/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hciassignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

/**
 * Purpose: Used to create a User object to pass data around the program
 * Author: Aaron Gangemi
 */
public class User 
{
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String username;
    private String password;
    private String confirmedPassword;
    private Color usernameColour;
    private String backgroundColour;
    private String usernameColorText;
    private ObservableList<Server> serverList;
    private ObservableList<Script> listOfScripts;

    /***********************************************
     * Purpose: Create a user and used to store details on create account
     * @param firstName
     * @param lastName
     * @param emailAddress
     * @param username
     * @param password
     * @param confirmedPassword
     * @param usernameColour
     * @param backgroundColour
     * @param usernameColorText 
     */
    public User(String firstName, String lastName, 
            String emailAddress, String username, 
            String password, String confirmedPassword,
            Color usernameColour, String backgroundColour,
            String usernameColorText)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.usernameColour = usernameColour;
        this.backgroundColour = backgroundColour;
        this.usernameColorText = usernameColorText;
        serverList = FXCollections.observableArrayList();
        listOfScripts = FXCollections.observableArrayList();
        
    }

    public ObservableList<Server> getServerList() {
        return serverList;
    }

    public void setServerList(ObservableList<Server> serverList) {
        this.serverList = serverList;
    }
    /***********************************************
     * Accessors and Mutators for classfields
     */
    
    
    public String getUsernameColorText()
    {
        return usernameColorText;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getEmailAddress()
    {
        return emailAddress;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public String getConfirmedPassword()
    {
        return confirmedPassword;
    }
    public Color getUsernameColour(){
        return usernameColour;
    }
    public String getBackgroundColour(){
        return backgroundColour;
    }
    public void setFirstName(String inFirstName)
    {
        firstName = inFirstName;
    }
    public void setLastName(String inLastName)
    {
        lastName = inLastName;
    }
    public void setEmailAddress(String inEmail)
    {
        emailAddress = inEmail;
    }
    public void setUsername(String inUsername)
    {
        username = inUsername;
    }
    public void setUsernameColour(Color inColour)
    {
        usernameColour = inColour;
    }
    public void setBackgroundColour(String backgroundColour)
    {
        this.backgroundColour = backgroundColour;
    }
    public void setUsernameColorText(String usernameColorText)
    {
        this.usernameColorText = usernameColorText;
    }
            
    public ObservableList<Script> getListOfScripts() {
        return listOfScripts;
    }

    public void setListOfScripts(ObservableList<Script> listOfScripts) {
        this.listOfScripts = listOfScripts;
    }
}
