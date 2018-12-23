package com.example.rahul.hit.util;

public class Users {

    public String fullname;
    public String roomno;
    public String email;
    public String username;
    public String password;
    public String confirmpassword;


    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }


    public Users(){

    }
    public Users(String fullname, String roomno, String email, String username, String password, String confirmpassword){
        this.fullname=fullname;
        this.roomno=roomno;
        this.email=email;
        this.username=username;
        this.password=password;
        this.confirmpassword=confirmpassword;


    }
}
