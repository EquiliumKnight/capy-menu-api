package com.capymenu.capy_menu_api.models.enums;

public enum UserRole {
    ADMIN("ADMIN"), 
    USER("USER");

    public final String roleName;

    private UserRole(String name) {
        this.roleName = name;
    }

    @Override
    public String toString() {
        return this.roleName;
    }
}
