package com.mike.crud.servicesHibernate;

import com.mike.crud.model.Developer;
import com.mike.crud.model.Skill;
import com.mike.crud.model.Specialty;
import com.mike.crud.model.Status;
import com.mike.crud.repository.entity.DevSkillsEntity;
import com.mike.crud.repository.entity.DeveloperEntity;
import com.mike.crud.repository.entity.SkillEntity;
import com.mike.crud.repository.entity.SpecialtyEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;


public class DeveloperServicesHib {
//    SessionFactory factoryDev = new Configuration().configure("hibernate.cfg.xml")
//            .addAnnotatedClass(DeveloperEntity.class).buildSessionFactory();
//
//    SessionFactory factorySpec = new Configuration().configure("hibernate.cfg.xml")
//            .addAnnotatedClass(SpecialtyEntity.class).buildSessionFactory();
//
//    SessionFactory factorySkill = new Configuration().configure("hibernate.cfg.xml")
//            .addAnnotatedClass(SkillEntity.class).buildSessionFactory();
//
//    SessionFactory factoryDevSkill = new Configuration().configure("hibernate.cfg.xml")
//            .addAnnotatedClass(DevSkillsEntity.class).buildSessionFactory();
//
//    Session sessionDev = factoryDev.getCurrentSession();
//    Session sessionSpec = factorySpec.getCurrentSession();
//    Session sessionSkill = factorySkill.getCurrentSession();
//    Session sessionDevSkill = factoryDevSkill.getCurrentSession();


    SessionFactory factoryDev;
    SessionFactory factorySpec;
    SessionFactory factorySkill;
    SessionFactory factoryDevSkill;
    Session sessionDev;
    Session sessionSpec;
    Session sessionSkill;
    Session sessionDevSkill;

    public Developer createDeveloper(Developer developer) {
        factoryDev = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(DeveloperEntity.class).buildSessionFactory();
        factorySpec = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(SpecialtyEntity.class).buildSessionFactory();
        factorySkill = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(SkillEntity.class).buildSessionFactory();
        factoryDevSkill = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(DevSkillsEntity.class).buildSessionFactory();
        sessionDev = factoryDev.getCurrentSession();
        sessionSpec = factorySpec.getCurrentSession();
        sessionSkill = factorySkill.getCurrentSession();
        sessionDevSkill = factoryDevSkill.getCurrentSession();

        try {
            sessionDev.beginTransaction();
            sessionSpec.beginTransaction();
            sessionSkill.beginTransaction();
            sessionDevSkill.beginTransaction();
            DeveloperEntity developerEntity = new DeveloperEntity();
            developerEntity.setFirstName(developer.getFirstName());
            developerEntity.setLastName(developer.getLastName());
            SpecialtyEntity specialtyEntity = sessionSpec.get(SpecialtyEntity.class, developer.getSpecialty().getId());
            developerEntity.setSpecialty(specialtyEntity);
            developerEntity.setStatus(developer.getStatus().toString());

            List<SkillEntity> skillEntityList = new ArrayList<>();
            for (Skill s : developer.getSkills()) {
                skillEntityList.add(sessionSkill.get(SkillEntity.class, s.getId()));
            }

            for (SkillEntity se : skillEntityList) {
                DevSkillsEntity devSkillsEntity = new DevSkillsEntity(developerEntity, se);
                sessionDevSkill.save(devSkillsEntity);
            }
            sessionSpec.getTransaction().commit();
            sessionSkill.getTransaction().commit();
            sessionDev.save(developerEntity);
            sessionDev.getTransaction().commit();
            sessionDevSkill.getTransaction().commit();
        } finally {
            factoryDev.close();
            factorySkill.close();
            factorySpec.close();
            factoryDevSkill.close();
            sessionDev.close();
            sessionSpec.close();
            sessionSkill.close();
            sessionDevSkill.close();
        }
        return developer;
    }

    public Developer updateDeveloper(Developer developer) {
        factoryDev = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DeveloperEntity.class).buildSessionFactory();
        factorySpec = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SpecialtyEntity.class).buildSessionFactory();
        factorySkill = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SkillEntity.class).buildSessionFactory();
        factoryDevSkill = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DevSkillsEntity.class).buildSessionFactory();
        sessionDev = factoryDev.getCurrentSession();
        sessionSpec = factorySpec.getCurrentSession();
        sessionSkill = factorySkill.getCurrentSession();
        sessionDevSkill = factoryDevSkill.getCurrentSession();
        try {
            sessionDev.beginTransaction();
            sessionSpec.beginTransaction();
            sessionSkill.beginTransaction();
            sessionDevSkill.beginTransaction();
            DeveloperEntity developerEntity = sessionDev.get(DeveloperEntity.class,developer.getId());
            developerEntity.setFirstName(developer.getFirstName());
            developerEntity.setLastName(developer.getLastName());
            SpecialtyEntity specialtyEntity = sessionSpec.get(SpecialtyEntity.class, developer.getSpecialty().getId());
            developerEntity.setSpecialty(specialtyEntity);
            developerEntity.setStatus(developer.getStatus().toString());

            List<SkillEntity> skillEntityList = new ArrayList<>();
            for (Skill s : developer.getSkills()) {
                skillEntityList.add(sessionSkill.get(SkillEntity.class, s.getId()));
            }

            List<DevSkillsEntity> currentDevSkill = sessionDevSkill.createQuery("from DevSkillsEntity where id_developer="+developer.getId()).getResultList();

            for (DevSkillsEntity se: currentDevSkill) {
                if(!skillEntityList.contains(se)) {
                    sessionDevSkill.delete(se);
                }
            }

            for (SkillEntity se : skillEntityList) {
                DevSkillsEntity devSkillsEntity = new DevSkillsEntity(developerEntity, se);
                if(!currentDevSkill.contains(se)) {
                    sessionDevSkill.save(devSkillsEntity);
                }
            }

            sessionSpec.getTransaction().commit();
            sessionSkill.getTransaction().commit();
            sessionDev.getTransaction().commit();
            sessionDevSkill.getTransaction().commit();
        } finally {
            factoryDev.close();
            factorySkill.close();
            factorySpec.close();
            factoryDevSkill.close();
            sessionDev.close();
            sessionSpec.close();
            sessionSkill.close();
            sessionDevSkill.close();
        }
        return developer;
    }

    public void deleteDeveloper(Integer id) {
        factoryDev = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DeveloperEntity.class).buildSessionFactory();
        factorySkill = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SkillEntity.class).buildSessionFactory();
        factoryDevSkill = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DevSkillsEntity.class).buildSessionFactory();
        sessionDev = factoryDev.getCurrentSession();
        sessionSkill = factorySkill.getCurrentSession();
        sessionDevSkill = factoryDevSkill.getCurrentSession();
        try{
            sessionDev.beginTransaction();
            sessionDevSkill.beginTransaction();
            DeveloperEntity developerEntity = sessionDev.get(DeveloperEntity.class, id);
            List<DevSkillsEntity> devSkillsEntity = sessionDevSkill.createQuery("from DevSkillsEntity where id_developer="+id).getResultList();
            for(DevSkillsEntity ds:devSkillsEntity) {
                sessionDevSkill.delete(ds);
            }
            sessionDevSkill.getTransaction().commit();
            sessionDev.delete(developerEntity);
            sessionDev.getTransaction().commit();
        } finally {
            factoryDev.close();
            factorySkill.close();
            factoryDevSkill.close();
            sessionDev.close();
            sessionSkill.close();
            sessionDevSkill.close();
        }
    }

    public List<Developer> getAllDeveloper() {
        factoryDev = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DeveloperEntity.class).buildSessionFactory();
        factoryDevSkill = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DevSkillsEntity.class).buildSessionFactory();
        sessionDev = factoryDev.getCurrentSession();
        sessionDevSkill = factoryDevSkill.getCurrentSession();

        List<Developer> developerList = new ArrayList<>();
        try{
            sessionDev.beginTransaction();
            sessionDevSkill.beginTransaction();
            List<DeveloperEntity> developerEntityList = sessionDev.createQuery("from DeveloperEntity").getResultList();



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
                    List<DevSkillsEntity> devSkillsEntityList =
                            sessionDevSkill.createQuery("from DevSkillsEntity where id_developer="+de.getId()).getResultList();
                    for(DevSkillsEntity dse:devSkillsEntityList) {
                        SkillEntity skillEntity = dse.getSkillEntity();
                        Skill skill = new Skill();
                        skill.setId(skillEntity.getId());
                        skill.setSkill(skillEntity.getSkill());
                        skill.setStatus(Status.valueOf(skillEntity.getStatus()));
                        skillList.add(skill);
                    }
                }
                developer.setSkills(skillList);
                developerList.add(developer);
            }
            sessionDevSkill.getTransaction().commit();
            sessionDev.getTransaction().commit();
        } finally {
            factoryDev.close();
            factoryDevSkill.close();
            sessionDev.close();
            sessionDevSkill.close();
        }
        return developerList;
    }

    public Developer getDeveloperById(Integer id) {
        factoryDev = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DeveloperEntity.class).buildSessionFactory();
        factoryDevSkill = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DevSkillsEntity.class).buildSessionFactory();
        sessionDev = factoryDev.getCurrentSession();
        sessionDevSkill = factoryDevSkill.getCurrentSession();

        Developer developer = new Developer();
        try {
            sessionDev.beginTransaction();
            sessionDevSkill.beginTransaction();
            DeveloperEntity developerEntity = sessionDev.get(DeveloperEntity.class, id);
            List<DevSkillsEntity> devSkillsEntityList = sessionDevSkill.createQuery("from DevSkillsEntity where id_developer="+id).getResultList();
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
            for(DevSkillsEntity dse:devSkillsEntityList) {
                Skill skill = new Skill();
                skill.setId(dse.getSkillEntity().getId());
                skill.setSkill(dse.getSkillEntity().getSkill());
                skill.setStatus(Status.valueOf(dse.getSkillEntity().getStatus()));
                skillList.add(skill);
            }
            developer.setSkills(skillList);
            sessionDev.getTransaction().commit();
            sessionDevSkill.getTransaction().commit();
        } finally {
            factoryDev.close();
            factoryDevSkill.close();
            sessionDev.close();
            sessionDevSkill.close();
        }
        return developer;
    }
}
