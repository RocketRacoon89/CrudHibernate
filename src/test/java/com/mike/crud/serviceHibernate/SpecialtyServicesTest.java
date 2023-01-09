package com.mike.crud.serviceHibernate;

import com.mike.crud.model.Specialty;
import com.mike.crud.model.Status;
import com.mike.crud.repository.SpecialtyRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SpecialtyServicesTest {

//        SessionFactory factory = Mockito.mock(SessionFactory.class);
//        Session session = Mockito.mock(Session.class);
//
//        SpecialtyRepo specialtyServices = new SpecialtyRepo();
//
//    private Specialty getActiveSpecialty() {
//        Specialty specialty = new Specialty();
//        specialty.setId(1);
//        specialty.setSpecialty("Test");
//        specialty.setStatus(Status.ACTIVE);
//        return specialty;
//    }
//
//    @Test
//    public void createSpecialty() {
//        specialtyServices.setFactory(factory);
//        specialtyServices.setSession(session);
//        Specialty specialtyToSave = new Specialty();
//        specialtyToSave.setSpecialty("php");
//        specialtyToSave.setStatus(Status.ACTIVE);
//        when(specialtyServices.createSpecialty(any())).thenReturn(getActiveSpecialty());
//
//        Specialty createdSpecialty = specialtyServices.createSpecialty(specialtyToSave);
//        assertEquals(createdSpecialty.getStatus(), Status.ACTIVE);
//        assertNotNull(createdSpecialty.getId());
//
//    }


}
