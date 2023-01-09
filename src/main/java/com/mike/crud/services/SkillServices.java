package com.mike.crud.services;

import com.mike.crud.model.Skill;
import com.mike.crud.repository.SkillRepo;

import java.util.List;

public class SkillServices {

    private final SkillRepo skillRepo;

    public SkillServices() {
        this.skillRepo = new SkillRepo();
    }

    public Skill createSkill(Skill skill) {
        return skillRepo.createSkill(skill);
    }

    public Skill updateSkill(Skill skill) {
        return skillRepo.updateSkill(skill);
    }

    public void deleteSkill(Integer id) {
        skillRepo.deleteSkill(id);
    }

    public List<Skill> getAllSkills() {
        return skillRepo.getAllSkills();
    }

    public Skill getByIdSkill(Integer id) {
        return skillRepo.getByIdSkill(id);
    }
}
