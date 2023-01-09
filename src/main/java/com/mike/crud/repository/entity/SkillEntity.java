package com.mike.crud.repository.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="\"DB_Crud_Hiberante\".\"Skills\"")
public class SkillEntity {

    public SkillEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="skill")
    private String skill;

    @Column(name="status")
    private String status;

    @ManyToMany
    @JoinTable(name="\"DB_Crud_Hiberante\".\"developer_skills\"",
            joinColumns = @JoinColumn(name ="id_skill"),
            inverseJoinColumns = @JoinColumn(name = "id_developer"))
    private List<DeveloperEntity> developers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DeveloperEntity> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<DeveloperEntity> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "SkillEntity{" +
                "id=" + id +
                ", skill='" + skill + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
