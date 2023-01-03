package com.mike.crud.repository.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="\"DB_Crud_Hiberante\".\"developer_skills\"")
public class DevSkillsEntity implements Serializable {

    public DevSkillsEntity() {
    }

    public DevSkillsEntity(DeveloperEntity developerEntity, SkillEntity skillEntity) {
        this.developerEntity = developerEntity;
        this.skillEntity = skillEntity;
    }

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_developer")
    private DeveloperEntity developerEntity;

    @Id
    @OneToOne
    @JoinColumn(name="id_skill")
    private SkillEntity skillEntity;

    public DeveloperEntity getDeveloperEntity() {
        return developerEntity;
    }

    public void setDeveloperEntity(DeveloperEntity developerEntity) {
        this.developerEntity = developerEntity;
    }

    public SkillEntity getSkillEntity() {
        return skillEntity;
    }

    public void setSkillEntity(SkillEntity skillEntity) {
        this.skillEntity = skillEntity;
    }


}
