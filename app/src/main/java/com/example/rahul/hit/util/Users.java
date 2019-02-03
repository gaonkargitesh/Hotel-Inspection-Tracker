package com.example.rahul.hit.util;

public class Users {

    private String firstname;
    private String roomno;
    private String email;
    private String lastname;
    private String password;
    private String confirmpassword;


    public Users(String lastname, String password) {
        this.lastname = lastname;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }


    public Users(){

    }
    public Users(String firstname, String roomno, String email, String lastname, String password, String confirmpassword){
        this.firstname = firstname;
        this.roomno=roomno;
        this.email=email;
        this.lastname = lastname;
        this.password=password;
        this.confirmpassword=confirmpassword;


    }
}
