package com.cognixia.furniture_store.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    public static enum Role {
        ROLE_USER, ROLE_ADMIN   
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(unique = true, nullable = false)
    @Schema(description="username to be used with login")
    @NotBlank
    private String username;

    @Column(nullable = false)
    @Schema(description="password to be used with login")
    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    @Schema(description="is this a user or admin")
    @Column(nullable = false)
    private Role role;

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }

    
    
}
