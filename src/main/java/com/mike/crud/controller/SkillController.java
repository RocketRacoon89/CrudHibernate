package com.mike.crud.controller;

import com.mike.crud.model.Skill;
import com.mike.crud.model.Status;
import com.mike.crud.servicesHibernate.SkillServicesHib;

import java.util.List;

public class SkillController {
private SkillServicesHib skillService = new SkillServicesHib();

    public Skill createSkill(String name, String status) {
        Skill skill = new Skill();
        skill.setSkill(name);
        skill.setStatus(Status.valueOf(status));
        return skillService.createSkill(skill);
    }

    public Skill updateSkill(Integer id, String name, String status) {
        Skill skill = new Skill();
        skill.setId(id);
        skill.setSkill(name);
        skill.setStatus(Status.valueOf(status));
        return skillService.updateSkill(skill);
    }

    public void deleteSkill(Integer id) {
        skillService.deleteSkill(id);
    }

    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    public Skill getSkill(Integer id) {
        return skillService.getByIdSkill(id);
    }

}
