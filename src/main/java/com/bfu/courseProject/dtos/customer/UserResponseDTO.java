package com.bfu.courseProject.dtos.customer;

import com.bfu.courseProject.entities.User;

import java.time.Instant;

public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String email;
    private Instant creationDate;
    private Boolean isAdmin;

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public static UserResponseDTO responseFromCustomer(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setCity(user.getCity());
        userResponseDTO.setCreationDate(user.getCreationDate());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setPhoneNumber(user.getPhoneNumber());
        userResponseDTO.setAdmin(user.getAdmin());
        return userResponseDTO;
    }

}
