/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hciassignment;

/**
 * Purpose: To create a server object for the table
 * @author Aaron Gangemi
 */
public class Server {
    private String ServerName;
    
    public Server(String name)
    {
        ServerName = name;
    }

    //Getters and Setters for server below
    public String getServerName() {
        return ServerName;
    }

    public void setServerName(String ServerName) {
        this.ServerName = ServerName;
    }
}
