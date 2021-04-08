package com.example.assignmentnew;

public class Person {
   private String email ;
   private String pass ;
   private String firstname ;
  private   String lastname ;
   private String city ;
   private String mobilenumber ;
   private String gender ;
   private String date ;

    public Person(String email, String pass, String firstname, String lastname, String city, String mobilenumber, String gender, String date) {
        this.email = email;
        this.pass = pass;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.mobilenumber = mobilenumber;
        this.gender = gender;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
