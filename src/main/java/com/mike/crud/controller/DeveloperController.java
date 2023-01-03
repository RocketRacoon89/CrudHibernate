package com.mike.crud.controller;

import com.mike.crud.model.Developer;
import com.mike.crud.model.Skill;
import com.mike.crud.model.Status;
import com.mike.crud.repository.entity.SkillEntity;
import com.mike.crud.servicesHibernate.DeveloperServicesHib;
import com.mike.crud.servicesHibernate.SkillServicesHib;
import com.mike.crud.servicesHibernate.SpecialtyServicesHib;

import java.util.ArrayList;
import java.util.List;

public class DeveloperController {

    private DeveloperServicesHib developerService = new DeveloperServicesHib();
    private SkillServicesHib skillService = new SkillServicesHib();
    private SpecialtyServicesHib specialtyService = new SpecialtyServicesHib();

    public Developer createDeveloper(String firstName, String lastName, List<Integer> skillID, Integer specialty, String status) {
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        List<Skill> skills = new ArrayList<>();
//        skillID.stream().forEach(s-> skills.add(skillService.getByIdSkill(s)));
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
//        skillID.stream().forEach(s-> skills.add(skillService.getByIdSkill(s)));
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
        return developerService.getAllDeveloper();
    }

    public Developer getDeveloper(Integer id) {
        return developerService.getDeveloperById(id);
    }

}