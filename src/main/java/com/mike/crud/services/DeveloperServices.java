package com.mike.crud.services;

import com.mike.crud.model.Developer;
import com.mike.crud.repository.DeveloperRepo;

import java.util.List;

public class DeveloperServices {

    private final DeveloperRepo developerRepo;

    public DeveloperServices() {
        this.developerRepo = new DeveloperRepo();
    }

    public DeveloperServices(DeveloperRepo developerRepo) {
        this.developerRepo = developerRepo;
    }

    public Developer createDeveloper(Developer developer) {
        return developerRepo.createDeveloper(developer);
    }

    public Developer updateDeveloper(Developer developer) {
        return developerRepo.updateDeveloper(developer);
    }

    public void deleteDeveloper(Integer id) {
        developerRepo.deleteDeveloper(id);
    }

    public List<Developer> getAllDevelopers() {
        return developerRepo.getAllDeveloper();
    }

    public Developer getDeveloperById(Integer id) {
        return developerRepo.getDeveloperById(id);
    }
}
