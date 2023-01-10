package com.mike.crud.serviceHibernate;

import com.mike.crud.model.Developer;
import com.mike.crud.model.Specialty;
import com.mike.crud.model.Status;
import com.mike.crud.repository.DeveloperRepo;
import com.mike.crud.services.DeveloperServices;
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

public class DeveloperServicesTest {
    DeveloperRepo developerRepository = Mockito.mock(DeveloperRepo.class);
    DeveloperServices developerService = new DeveloperServices(developerRepository);

    private Developer getTestDeveloper() {
        Developer developer = new Developer();
        developer.setId(1);
        developer.setFirstName("Test");
        developer.setLastName("Test");
        developer.setSkills(new ArrayList<>());
        developer.setSpecialty(new Specialty());
        developer.setStatus(Status.ACTIVE);
        return developer;
    }

    @Test
    public void createDeveloper() {
        Developer developer = new Developer();
        developer.setId(1);
        developer.setFirstName("Test");
        developer.setLastName("Test");
        developer.setSkills(new ArrayList<>());
        developer.setSpecialty(new Specialty());
        developer.setStatus(Status.ACTIVE);
        when(developerRepository.createDeveloper(any())).thenReturn(getTestDeveloper());

        Developer createdDeveloper = developerService.createDeveloper(developer);
        assertEquals(createdDeveloper.getStatus(),Status.ACTIVE);
        assertNotNull(createdDeveloper.getId());
    }

    @Test
    public void failedCreateDeveloper() {
        Developer developer = new Developer();
        developer.setId(1);
        developer.setFirstName("Test");
        developer.setLastName("Test");
        developer.setSkills(new ArrayList<>());
        developer.setSpecialty(new Specialty());
        developer.setStatus(Status.ACTIVE);

        try{
            when(developerRepository.createDeveloper(any())).thenThrow(new SQLException("SQL Exception"));
            developerService.createDeveloper(developer);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("SQL Exception"));
        }
    }

    @Test
    public void updateDeveloper() {
        Developer developer = new Developer();
        developer.setId(1);
        developer.setFirstName("Test");
        developer.setLastName("Test");
        developer.setSkills(new ArrayList<>());
        developer.setSpecialty(new Specialty());
        developer.setStatus(Status.ACTIVE);
        when(developerRepository.updateDeveloper(any())).thenReturn(getTestDeveloper());

        Developer updateDeveloper = developerService.updateDeveloper(developer);
        assertEquals(updateDeveloper.getStatus(),Status.ACTIVE);
        assertNotNull(updateDeveloper.getId());
    }

    @Test
    public void failedUpdateDeveloper() {
        Developer developer = new Developer();
        developer.setId(1);
        developer.setFirstName("Test");
        developer.setLastName("Test");
        developer.setSkills(new ArrayList<>());
        developer.setSpecialty(new Specialty());
        developer.setStatus(Status.ACTIVE);

        try{
            when(developerRepository.updateDeveloper(any())).thenThrow(new SQLException("SQL Exception"));
            developerService.createDeveloper(developer);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("SQL Exception"));
        }
    }

    @Test
    public void deleteDeveloper() {
        developerService.deleteDeveloper(isA(Integer.class));
        Mockito.verify(developerRepository, Mockito.times(1)).deleteDeveloper(isA(Integer.class));
    }

    @Test
    public void failedDeleteDeveloper() {
        try{
            Mockito.doThrow(new SQLException("SQL Exception")).when(developerRepository).deleteDeveloper(isA(Integer.class));
            developerService.deleteDeveloper(isA(Integer.class));
        }catch (Exception e) {
            assertTrue(e.getMessage().contains("SQL Exception"));
        }


    }

    @Test
    public void getAllDevelopers() {
        List<Developer> developerList = new ArrayList<>();
        developerList.add(getTestDeveloper());
        when(developerRepository.getAllDeveloper()).thenReturn(developerList);
        assertNotNull(developerList.get(0).getId());
    }

    @Test
    public void failedGetAllDeveloper() {
        try{
            when(developerRepository.getAllDeveloper()).thenThrow(new NullPointerException());
            developerService.getAllDevelopers();
        }catch (NullPointerException e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void getDeveloperById() {
        when(developerRepository.getDeveloperById(any())).thenReturn(getTestDeveloper());
        Developer developer = developerService.getDeveloperById(isA(Integer.class));
        assertNotNull(developer.getId());
    }

    @Test
    public void failedGetDeveloperById() {
        try{
            when(developerRepository.getDeveloperById(isA(Integer.class))).thenThrow(new NullPointerException());
            developerService.getDeveloperById(isA(Integer.class));
        }catch (NullPointerException e) {
            assertTrue(e instanceof NullPointerException);
        }
    }
}
