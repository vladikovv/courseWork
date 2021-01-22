package com.bfu.courseProject.security;

public class CustomUserPrincipal {
    private Long id;
    private String email;
    private boolean isAdmin;

    public CustomUserPrincipal(Long id, String email, boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
