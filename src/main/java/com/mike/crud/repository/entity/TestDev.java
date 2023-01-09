package com.mike.crud.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"DB_Crud_Hiberante\".\"Developers\"")
public class TestDev {

    public TestDev() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @ManyToOne()
    @JoinColumn(name="id_specialty")
    private SpecialtyEntity specialty;

    @Column(name="status")
    private String status;

    @ManyToMany
    @JoinTable(name="\"DB_Crud_Hiberante\".\"developer_skills\"",
             joinColumns = @JoinColumn(name ="id_developer"),
            inverseJoinColumns = @JoinColumn(name = "id_skill"))
    private List<SkillEntity> skills;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SpecialtyEntity getSpecialty() {
        return specialty;
    }

    public void setSpecialty(SpecialtyEntity specialty) {
        this.specialty = specialty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SkillEntity> getListSpec() {
        return skills;
    }

    public void setListSpec(List<SkillEntity> listSpec) {
        this.skills = listSpec;
    }

    @Override
    public String toString() {
        return "TestDev{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty=" + specialty +
                ", status='" + status + '\'' +
                '}';
    }
}
