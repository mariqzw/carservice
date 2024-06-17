package com.example.springdatabasicdemo.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends TimeStampEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String imageUrl;
    private boolean is_active;
    @ManyToOne(optional = false)
    private Role role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Offer> offers;

    public User(String username, String password, String firstName, String lastName, String imageUrl) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
    }

    protected User(){
    }


    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "is_active", nullable = false)
    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @Column(name = "imageUrl", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable=false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }


    @Override
    public String toString() {
        return "User { id=" + id + ", is_active=" + is_active + ", first_name=" + firstName + ", last_name=" + lastName + ", password=" + password + ", username=" + username + " }";
    }
}
