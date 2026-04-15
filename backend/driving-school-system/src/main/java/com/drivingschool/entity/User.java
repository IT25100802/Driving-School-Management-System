package com.drivingschool.entity;

public abstract class User {
    private String id;
    private String name;
    private String email;
    private String phone;

    //Default Constructor
    public User() {}

    //Parameterized Constructor
    public User(String id,String name,String email,String phone){
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;
    }

    //Abstraction
    public abstract String getRole();

    //Polymmorphism
    public String getDetails(){
        return "ID: " +id + " | Name: " +name + " | Role: " +getRole();
    }

    //Getter and Setter
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
