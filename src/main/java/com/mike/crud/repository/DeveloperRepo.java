package com.mike.crud.repository;

import com.mike.crud.model.Developer;
import com.mike.crud.model.Skill;
import com.mike.crud.model.Specialty;
import com.mike.crud.model.Status;
import com.mike.crud.repository.entity.DeveloperEntity;
import com.mike.crud.repository.entity.SkillEntity;
import com.mike.crud.repository.entity.SpecialtyEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;


public class DeveloperRepo {

    SessionFactory factory;
    Session session;

    public Developer createDeveloper(Developer developer) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DeveloperEntity.class)
                .addAnnotatedClass(SkillEntity.class)
                .addAnnotatedClass(SpecialtyEntity.class)
                .buildSessionFactory();

        session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            DeveloperEntity developerEntity = new DeveloperEntity();
            developerEntity.setFirstName(developer.getFirstName());
            developerEntity.setLastName(developer.getLastName());
            SpecialtyEntity specialtyEntity = session.get(SpecialtyEntity.class, developer.getSpecialty().getId());
            developerEntity.setSpecialty(specialtyEntity);
            developerEntity.setStatus(developer.getStatus().toString());

            List<SkillEntity> skillEntityList = new ArrayList<>();
            for (Skill s : developer.getSkills()) {
                skillEntityList.add(session.get(SkillEntity.class, s.getId()));
            }

            developerEntity.setSkills(skillEntityList);

            session.save(developerEntity);
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return developer;
    }

    public Developer updateDeveloper(Developer developer) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DeveloperEntity.class)
                .addAnnotatedClass(SkillEntity.class)
                .addAnnotatedClass(SpecialtyEntity.class)
                .buildSessionFactory();

        session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            DeveloperEntity developerEntity = session.get(DeveloperEntity.class,developer.getId());
            developerEntity.setFirstName(developer.getFirstName());
            developerEntity.setLastName(developer.getLastName());
            SpecialtyEntity specialtyEntity = session.get(SpecialtyEntity.class, developer.getSpecialty().getId());
            developerEntity.setSpecialty(specialtyEntity);
            developerEntity.setStatus(developer.getStatus().toString());

            List<SkillEntity> newSkillList = new ArrayList<>();
            for (Skill s : developer.getSkills()) {
                newSkillList.add(session.get(SkillEntity.class, s.getId()));
            }

            developerEntity.setSkills(newSkillList);

//            DeveloperEntity currentDeveloper = session.get(DeveloperEntity.class, developer.getId());
//
//            List<SkillEntity> currentSkillList = currentDeveloper.getSkills();
//
//            for (SkillEntity se: currentSkillList) {
//                if(!newSkillList.contains(se)) {
//                    session.delete(se);
//                }
//            }
//
//            for (SkillEntity se : newSkillList) {
//                if(!currentSkillList.contains(se)) {
//                    session.save(se);
//                }
//            }
            session.save(developerEntity);
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return developer;
    }

    public void deleteDeveloper(Integer id) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DeveloperEntity.class)
                .addAnnotatedClass(SkillEntity.class)
                .buildSessionFactory();
        session = factory.getCurrentSession();
        try{
            session.beginTransaction();
            DeveloperEntity developerEntity = session.get(DeveloperEntity.class, id);
            session.delete(developerEntity);
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
    }

    public List<Developer> getAllDeveloper() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DeveloperEntity.class)
                .buildSessionFactory();
        session = factory.getCurrentSession();

        List<Developer> developerList = new ArrayList<>();
        try{
            session.beginTransaction();
            List<DeveloperEntity> developerEntityList = session.createQuery("from DeveloperEntity").getResultList();
            for(DeveloperEntity de:developerEntityList) {
                Developer developer = new Developer();
                developer.setId(de.getId());
                developer.setFirstName(de.getFirstName());
                developer.setLastName(de.getLastName());
                Specialty specialty = new Specialty();
                specialty.setId(de.getSpecialty().getId());
                specialty.setSpecialty(de.getSpecialty().getSpecialty());
                specialty.setStatus(Status.valueOf(de.getSpecialty().getStatus()));
                developer.setSpecialty(specialty);
                developer.setStatus(Status.valueOf(de.getStatus()));
                List<Skill> skillList = new ArrayList<>();
                if(developer.getSkills()==null) {
                    List<SkillEntity> devSkillsList = de.getSkills();
                    for(SkillEntity se:devSkillsList) {
                        Skill skill = new Skill();
                        skill.setId(se.getId());
                        skill.setSkill(se.getSkill());
                        skill.setStatus(Status.valueOf(se.getStatus()));
                        skillList.add(skill);
                    }
                }
                developer.setSkills(skillList);
                developerList.add(developer);
            }
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return developerList;
    }

    public Developer getDeveloperById(Integer id) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DeveloperEntity.class)
                .buildSessionFactory();
        session = factory.getCurrentSession();

        Developer developer = new Developer();
        try {
            session.beginTransaction();
            DeveloperEntity developerEntity = session.get(DeveloperEntity.class, id);
            List<SkillEntity> devSkillList = developerEntity.getSkills();
            developer.setId(developerEntity.getId());
            developer.setFirstName(developerEntity.getFirstName());
            developer.setLastName(developerEntity.getLastName());
            developer.setStatus(Status.valueOf(developerEntity.getStatus()));
            Specialty specialty = new Specialty();
            specialty.setId(developerEntity.getSpecialty().getId());
            specialty.setSpecialty(developerEntity.getSpecialty().getSpecialty());
            specialty.setStatus(Status.valueOf(developerEntity.getSpecialty().getStatus()));
            developer.setSpecialty(specialty);
            List<Skill> skillList = new ArrayList<>();
            for(SkillEntity se:devSkillList) {
                Skill skill = new Skill();
                skill.setId(se.getId());
                skill.setSkill(se.getSkill());
                skill.setStatus(Status.valueOf(se.getStatus()));
                skillList.add(skill);
            }
            developer.setSkills(skillList);
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return developer;
    }
}
