package com.example.panchayatsandesh;

public class storingdata {
    String name, email, phone, password, aadhar, userimage;

    public storingdata() {
    }

    public storingdata(String name, String email, String phone, String password, String aadhar, String userimage) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.aadhar = aadhar;
        this.userimage = userimage;  // Set the userimage field
    }

    public storingdata(String fullname, String email, String phone, String password, String aadhar) {
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }
}
