package com.drivingschool.entity;

public class Admin extends User {

    private String password;
    private Role role;
    private String registeredDate;
    private String status;

    // Enum restricts role to only valid values
    public enum Role {
        ADMIN,
        MANAGER,
        INSTRUCTOR,
        STUDENT
    }

    // Default constructor
    public Admin() {
        super();
    }

    public Admin(String id, String name, String email, String phone,
                 String password, Role role,
                 String registeredDate, String status) {
        super(id, name, email, phone);
        this.password = password;
        this.role = role;
        this.registeredDate = registeredDate;
        this.status = status;
    }

    @Override
    public String getRole() {
        return role != null ? role.name() : "UNKNOWN";
    }

    @Override
    public String getDetails() {
        return super.getDetails() +
                " | Status: " + status +
                " | Registered: " + registeredDate;
    }

    public String toFileString() {
        return getId() + "|" + getName() + "|" + getEmail() + "|" +
                getPhone() + "|" + password + "|" + role + "|" +
                registeredDate + "|" + status;
    }

    public static Admin fromFileString(String line) {
        String[] parts = line.split("\\|");

        return new Admin(
                parts[0],
                parts[1],
                parts[2],
                parts[3],
                parts[4],
                Role.valueOf(parts[5]),
                parts[6],
                parts[7]
        );
    }

    // Getters & Setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRoleEnum() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}