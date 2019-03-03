package com.example.rahul.hit.util;

import android.text.TextUtils;

public class TechnicianModel {

    private String name;
    private String email;
    private String password;
    private String role;

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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getJobPro() {
        return jobPro;
    }

    public void setJobPro(String jobPro) {
        this.jobPro = jobPro;
    }

    private String phoneNo;
    private String jobPro;


    public TechnicianModel(){

    }


    public TechnicianModel(String name,String email,String phoneNo,String jobPro,String password,String role){
        this.name=name;
        this.email=email;
        this.phoneNo=phoneNo;
        this.jobPro=jobPro;
        this.password=password;
        this.role=role;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
