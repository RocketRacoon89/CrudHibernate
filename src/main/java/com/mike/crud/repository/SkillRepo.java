package com.mike.crud.repository;

import com.mike.crud.model.Skill;
import com.mike.crud.model.Status;
import com.mike.crud.repository.entity.SkillEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class SkillRepo {

    private SessionFactory factory;

    private Session session;

    public Skill createSkill(Skill skill) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SkillEntity.class).buildSessionFactory();
        session = factory.getCurrentSession();
        try {
            SkillEntity skillEntity = new SkillEntity();
            skillEntity.setSkill(skill.getSkill());
            skillEntity.setStatus(skill.getStatus().toString());
            session.beginTransaction();
            session.save(skillEntity);
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return skill;
    }

    public Skill updateSkill(Skill skill) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SkillEntity.class).buildSessionFactory();
        session = factory.getCurrentSession();
        try {
            SkillEntity skillEntity = new SkillEntity();
            session.beginTransaction();
            session.get(SkillEntity.class, skill.getId());
            skillEntity.setSkill(skill.getSkill());
            skillEntity.setStatus(skill.getStatus().toString());
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return skill;
    }

    public void deleteSkill(Integer id) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SkillEntity.class).buildSessionFactory();
        session = factory.getCurrentSession();
        try{
            SkillEntity skillEntity = new SkillEntity();
            session.beginTransaction();
            session.get(SkillEntity.class, id);
            session.delete(skillEntity);
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
    }

    public List<Skill> getAllSkills() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SkillEntity.class).buildSessionFactory();
        session = factory.getCurrentSession();
        List<Skill> allSkills = new ArrayList<>();
        try {
            session.beginTransaction();

            List<SkillEntity> skillList = session.createQuery("from SkillEntity").getResultList();
            for(SkillEntity s:skillList) {
                Skill skill = new Skill();
                skill.setId(s.getId());
                skill.setSkill(s.getSkill());
                skill.setStatus(Status.valueOf(s.getStatus()));
                allSkills.add(skill);
            }
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return allSkills;
    }

    public Skill getByIdSkill(Integer id) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(SkillEntity.class).buildSessionFactory();
        session = factory.getCurrentSession();
        Skill skill = new Skill();
        try{
            session.beginTransaction();
            SkillEntity skillEntity = session.get(SkillEntity.class, id);
            skill.setId(skillEntity.getId());
            skill.setSkill(skillEntity.getSkill());
            skill.setStatus(Status.valueOf(skillEntity.getStatus()));
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return skill;
    }
}
