/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hciassignment;

/**
 * Purpose: Used to store the connected users in the direct messages table
 * and the connected users table on the server page
 * @author Aaron Gangemi
 */
public class ConnectedUsers {
    
    private String name;
    //Store the connected user name
    public ConnectedUsers(String name)
    {
        this.name = name;
    }

    //Getters and setters for name below
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
