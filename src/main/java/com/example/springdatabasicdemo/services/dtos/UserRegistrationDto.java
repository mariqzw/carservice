package com.example.springdatabasicdemo.services.dtos;

import com.example.springdatabasicdemo.util.UniqueUsername;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class UserRegistrationDto {

    @UniqueUsername
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String imageUrl;
    private LocalDateTime created;
    private LocalDateTime modified;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String username, String firstName, String lastName, String password, String confirmPassword, String imageUrl) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.imageUrl = imageUrl;
    }

    @NotEmpty(message = "Username cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = "First name cannot be null or empty!")
    @Size(min = 2, max = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty(message = "Last name cannot be null or empty!")
    @Size(min = 3, max = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @NotEmpty(message = "Password cannot be null or empty!")
    @Size(min = 5, max = 25)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotEmpty(message = "Confirm Password cannot be null or empty!")
    @Size(min = 5, max = 25)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotEmpty(message = "Image url cannot be null or empty!")
    @Size(min = 4, max = 25)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }


}
