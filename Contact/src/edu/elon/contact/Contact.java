/*
Copyright (c) 2018 Christy Smith
Campus Box 9128, Elon University, Elon, NC 27244

Copyright (c) 2018 Evan Elkin
Campus Box ____, Elon University, Elon, NC 27244

File also came from Murach

 */
package edu.elon.contact;



public class Contact{

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String major;

    public Contact() {
        id=-1;
        email = "";
        firstName = "";
        lastName = "";
        middleName = "";
        major="";
    }

    public Contact(String firstName, String middleName, String lastName, String email, 
            String major) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.major = major;
    }
    
    public Contact(int id, String firstName, String middleName, String lastName, String email, 
            String major) {
    		this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the contact's middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param bookTitle the bookTitle to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the contact's major
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major the major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }
}