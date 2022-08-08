package com.mockproject.entity.modeljson;

import com.mockproject.entity.model.Roles;

import javax.persistence.*;

@Entity
public class RoleDataModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public RoleDataModel(Roles roles) {
        this.id = roles.getId();
        this.name = roles.getName();
        this.description = roles.getDescription();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
