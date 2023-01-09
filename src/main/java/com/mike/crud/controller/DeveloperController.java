package com.mike.crud.controller;

import com.mike.crud.model.Developer;
import com.mike.crud.model.Skill;
import com.mike.crud.model.Status;
import com.mike.crud.services.DeveloperServices;
import com.mike.crud.services.SkillServices;
import com.mike.crud.services.SpecialtyServices;

import java.util.ArrayList;
import java.util.List;

public class DeveloperController {

    private DeveloperServices developerService = new DeveloperServices();
    private SkillServices skillService = new SkillServices();
    private SpecialtyServices specialtyService = new SpecialtyServices();

    public Developer createDeveloper(String firstName, String lastName, List<Integer> skillID, Integer specialty, String status) {
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        List<Skill> skills = new ArrayList<>();
        for(Integer i:skillID) {
            Skill skill = skillService.getByIdSkill(i);
            skills.add(skill);
        }
        developer.setSkills(skills);
        developer.setSpecialty(specialtyService.getByIdSpecialty(specialty));
        developer.setStatus(Status.valueOf(status));
        return developerService.createDeveloper(developer);
    }

    public Developer updateDeveloper(Integer id, String firstName, String lastName, List<Integer> skillID, Integer specialty, String status) {
        Developer developer = new Developer();
        developer.setId(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        List<Skill> skills = new ArrayList<>();
        for(Integer i:skillID) {
            Skill skill = skillService.getByIdSkill(i);
            skills.add(skill);
        }
        developer.setSkills(skills);
        developer.setSpecialty(specialtyService.getByIdSpecialty(specialty));
        developer.setStatus(Status.valueOf(status));
        return developerService.updateDeveloper(developer);
    }

    public void deleteDeveloper(Integer id) {
        developerService.deleteDeveloper(id);
    }

    public List<Developer> getAllDevelopers() {
        return developerService.getAllDevelopers();
    }

    public Developer getDeveloper(Integer id) {
        return developerService.getDeveloperById(id);
    }

}