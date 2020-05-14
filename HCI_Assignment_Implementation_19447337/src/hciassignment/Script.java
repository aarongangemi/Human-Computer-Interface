/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hciassignment;

import javafx.scene.image.ImageView;

/**
 * Purpose: Create a script object
 * Author: Aaron Gangemi
 * Date Modified: 08/05/2020
 */
public class Script {
    private String name;
    private String date;
    private String description;
    private String command;
    /***************************************************
     * Purpose: The script constructor
     * @param name
     * @param date
     * @param description
     * @param command
     */
    public Script(String name, String date, String description,
            String command)
    {
        this.name = name;
        this.date= date;
        this.description = description;
        this.command = command;
    }

    //Getters and setters for script
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}