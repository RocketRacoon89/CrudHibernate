package com.mike.crud.services;

import com.mike.crud.model.Specialty;
import com.mike.crud.repository.SpecialtyRepo;

import java.util.List;

public class SpecialtyServices {
    private final SpecialtyRepo specialtyRepo;

    public SpecialtyServices() {
        this.specialtyRepo = new SpecialtyRepo();
    }

    public Specialty createSpecialty(Specialty specialty) {
        return specialtyRepo.createSpecialty(specialty);
    }

    public Specialty updateSpecialty(Specialty specialty) {
        return specialtyRepo.updateSpecialty(specialty);
    }

    public void deleteSpecialty(Integer id) {
        specialtyRepo.deleteSpecialty(id);
    }

    public List<Specialty> getAllSpecialty() {
        return specialtyRepo.getAllSpecialty();
    }

    public Specialty getByIdSpecialty(Integer id) {
        return specialtyRepo.getByIdSpecialty(id);
    }


}
