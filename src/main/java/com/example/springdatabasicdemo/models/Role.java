package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.enums.RoleEnum;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public Role(RoleEnum name) {
        this.name = name;
    }

    public Role() {
    }

    @Column(name = "name", nullable = false, length = 50)
    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}