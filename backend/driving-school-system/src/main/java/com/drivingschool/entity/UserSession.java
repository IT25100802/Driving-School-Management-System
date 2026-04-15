package com.drivingschool.entity;

public class UserSession {

    // Singleton pattern - one session at a time
    private static UserSession instance;

    private String userId;
    private String userName;
    private String role;
    private boolean loggedIn;

    //ENCAPSULATION
    private UserSession() {}

    //ABSTRACTION
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    // Login - set session data
    public void login(String userId, String userName, String role) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
        this.loggedIn = true;
    }

    // Logout - clear session data
    public void logout() {
        this.userId = null;
        this.userName = null;
        this.role = null;
        this.loggedIn = false;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getRole() { return role; }
    public boolean isLoggedIn() { return loggedIn; }
}