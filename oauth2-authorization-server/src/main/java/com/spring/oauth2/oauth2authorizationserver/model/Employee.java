package com.spring.oauth2.oauth2authorizationserver.model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idemployee;

    @Column(unique = true)
    private String email;

    private String name;

    public Employee(){}

    public int getIdemployee(){
        return idemployee;
    }

    public void setIdemployee(int idemployee){
        this.idemployee = idemployee;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
