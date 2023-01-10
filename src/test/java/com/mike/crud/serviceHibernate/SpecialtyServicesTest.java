package com.mike.crud.serviceHibernate;

import com.mike.crud.model.Specialty;
import com.mike.crud.model.Status;
import com.mike.crud.repository.SpecialtyRepo;
import com.mike.crud.services.SpecialtyServices;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

public class SpecialtyServicesTest {

    SpecialtyRepo specialtyRepository = Mockito.mock(SpecialtyRepo.class);
    SpecialtyServices specialtyService = new SpecialtyServices(specialtyRepository);

    private Specialty getActiveSpecialty() {
        Specialty specialty = new Specialty();
        specialty.setId(1);
        specialty.setSpecialty("Test");
        specialty.setStatus(Status.ACTIVE);
        return specialty;
    }

    @Test
    public void createSpecialty() {
        Specialty specialtyToSave = new Specialty();
        specialtyToSave.setSpecialty("php");
        specialtyToSave.setStatus(Status.ACTIVE);
        when(specialtyRepository.createSpecialty(any())).thenReturn(getActiveSpecialty());

        Specialty createdSpecialty = specialtyService.createSpecialty(specialtyToSave);
        assertEquals(createdSpecialty.getStatus(), Status.ACTIVE);
        assertNotNull(createdSpecialty.getId());

    }

    @Test
    public void failedCreateSpecialty() {
        Specialty specialtyToSave = new Specialty();
        specialtyToSave.setSpecialty("Java");
        specialtyToSave.setStatus(Status.ACTIVE);
        try {
            when(specialtyRepository.createSpecialty(specialtyToSave)).thenThrow(new SQLException("SQL Exception"));
            specialtyService.createSpecialty(specialtyToSave);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("SQL Exception"));
        }
    }

    @Test
    public void updateSpecialty() {
        Specialty specialtyToUpdate = new Specialty();
        specialtyToUpdate.setSpecialty("php");
        specialtyToUpdate.setStatus(Status.ACTIVE);
        when(specialtyRepository.updateSpecialty(any())).thenReturn(getActiveSpecialty());

        Specialty updateSpecialty = specialtyService.updateSpecialty(specialtyToUpdate);
        assertEquals(updateSpecialty.getStatus(),Status.ACTIVE);
        assertNotNull(updateSpecialty.getId());
    }

    @Test
    public void failedUpdateSpecialty() {
        Specialty specialtyToUpdate = new Specialty();
        specialtyToUpdate.setSpecialty("php");
        specialtyToUpdate.setStatus(Status.ACTIVE);
        try{
            when(specialtyRepository.updateSpecialty(any())).thenThrow(new SQLException("SQL Exception"));
            specialtyService.updateSpecialty(specialtyToUpdate);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("SQL Exception"));
        }
    }

    @Test
    public void deleteSpecialty() {
        specialtyService.deleteSpecialty(4321);
        Mockito.verify(specialtyRepository, Mockito.times(1)).deleteSpecialty(any());
    }

    @Test
    public void failedDeleteSpecialty() {
        try{
            Mockito.doThrow(new NullPointerException()).when(specialtyRepository).getByIdSpecialty(isA(Integer.class));
            specialtyService.deleteSpecialty(any());
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void getAllSpecialty() {
        List<Specialty> specList = new ArrayList<>();
        specList.add(getActiveSpecialty());
        when(specialtyRepository.getAllSpecialty()).thenReturn(specList);
        List<Specialty> allSpec = specialtyService.getAllSpecialty();
        assertNotNull(allSpec.get(0).getId());
    }

    @Test
    public void failedGetAllSpecialty() {
        try {
            when(specialtyRepository.getAllSpecialty()).thenThrow(new SQLException("SQL Exception"));
            specialtyService.getAllSpecialty();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("SQL Exception"));
        }
    }

    @Test
    public void getSpecialty() {
        when(specialtyRepository.getByIdSpecialty(any())).thenReturn(getActiveSpecialty());
        Specialty returnSpecialty = specialtyService.getByIdSpecialty(isA(Integer.class));
        assertNotNull(returnSpecialty.getId());
    }

    @Test
    public void failedGetSpecialty() {
        try{
            when(specialtyRepository.getByIdSpecialty(any())).thenThrow(new NullPointerException());
            specialtyService.getByIdSpecialty(any());
        } catch (NullPointerException e) {
            assertTrue(e instanceof NullPointerException);
        }
    }


}
