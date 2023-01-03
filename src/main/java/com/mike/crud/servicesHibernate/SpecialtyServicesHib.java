package com.mike.crud.servicesHibernate;

import com.mike.crud.model.Specialty;
import com.mike.crud.model.Status;
import com.mike.crud.repository.entity.SpecialtyEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class SpecialtyServicesHib {

//    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
//            .addAnnotatedClass(SpecialtyEntity.class).buildSessionFactory();
//
//    Session session = factory.getCurrentSession();

    private SessionFactory factory;

    private Session session;

    public Specialty createSpecialty(Specialty specialty) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SpecialtyEntity.class).buildSessionFactory();
        session = factory.getCurrentSession();
        try {
            SpecialtyEntity specialtyEntity = new SpecialtyEntity();
            specialtyEntity.setSpecialty(specialty.getSpecialty());
            specialtyEntity.setStatus(specialty.getStatus().toString());
            session.beginTransaction();
            session.save(specialtyEntity);
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return specialty;
    }

    public Specialty updateSpecialty(Specialty specialty) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SpecialtyEntity.class).buildSessionFactory();
        session = factory.getCurrentSession();
        try {
            SpecialtyEntity specialtyEntity = new SpecialtyEntity();
            session.beginTransaction();
            session.get(SpecialtyEntity.class, specialty.getId());
            specialtyEntity.setSpecialty(specialty.getSpecialty());
            specialtyEntity.setStatus(specialty.getStatus().toString());
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return specialty;
    }

    public void deleteSpecialty(Integer id) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SpecialtyEntity.class).buildSessionFactory();
        session = factory.getCurrentSession();
        try{
            SpecialtyEntity specialtyEntity = new SpecialtyEntity();
            session.beginTransaction();
            session.get(SpecialtyEntity.class, id);
            session.delete(specialtyEntity);
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
    }

    public List<Specialty> getAllSpecialty() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SpecialtyEntity.class).buildSessionFactory();
        session = factory.getCurrentSession();
        List<Specialty> allSpecialties = new ArrayList<>();
        try {
            session.beginTransaction();

            List<SpecialtyEntity> specialtyList = session.createQuery("from SpecialtyEntity").getResultList();
            for(SpecialtyEntity s:specialtyList) {
                Specialty specialty = new Specialty();
                specialty.setId(s.getId());
                specialty.setSpecialty(s.getSpecialty());
                specialty.setStatus(Status.valueOf(s.getStatus()));
                allSpecialties.add(specialty);
            }
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return allSpecialties;
    }

    public Specialty getByIdSpecialty(Integer id) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SpecialtyEntity.class).buildSessionFactory();
        session = factory.getCurrentSession();
        Specialty specialty = new Specialty();
        try{
            session.beginTransaction();
            SpecialtyEntity specialtyEntity = session.get(SpecialtyEntity.class, id);
            specialty.setId(specialtyEntity.getId());
            specialty.setSpecialty(specialtyEntity.getSpecialty());
            specialty.setStatus(Status.valueOf(specialtyEntity.getStatus()));
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return specialty;
    }
}
