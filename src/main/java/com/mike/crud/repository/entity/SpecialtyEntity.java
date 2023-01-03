package com.mike.crud.repository.entity;

import javax.persistence.*;
import javax.persistence.Entity;


@Entity
@Table(name="\"DB_Crud_Hiberante\".\"Specialties\"")
public class SpecialtyEntity {

    public SpecialtyEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="specialty")
    private String specialty;

    @Column(name="status")
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
