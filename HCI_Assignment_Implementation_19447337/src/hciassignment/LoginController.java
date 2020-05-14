/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hciassignment;

import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Purpose: Login Controller
 * Author: Aaron Gangemi
 * Date Modified: 08/05/2020
 * 
 */
public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Label PasswordLabel;

    /*****************************************************
     * Purpose: When the user clicks "Create Account", then proceed to 
     * create account page
     * @param event
     * @throws IOException 
     ******************************************************/
    @FXML
    private void clickCreateAccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                                                 "CreateAccount.fxml"));
        //Go to create account page
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    /********************************************************
     * Purpose: If user selects forgot details, then display alert
     * @param event 
     ********************************************************/
    @FXML
    void ForgotDetails(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);//Create Alert
            alert.setTitle("Forgot Username and Password"); //Set alert details
            alert.setHeaderText("You will receive an email to retrieve your"
                    + "username and password");
            alert.setContentText("However for this assignment, you may use any"
                    + " username and any password more than 8 characters");
            Optional<ButtonType> result = alert.showAndWait();
            
            //Display alert and wait for response
    }

    /*********************************************************
     * Purpose: When the user clicks login, user validation is performed and if
     * successful on validation, the user will proceed to the server page
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickLogin(ActionEvent event) throws IOException 
    {
        if(usernameField.getText().isEmpty() 
                || passwordField.getText().isEmpty()
                || usernameField.getText().equals("Enter Username*")
                || passwordField.getText().length() < 8)
            //Check if any of the fields are empty
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //Create alert stating invalid fields
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Please fill out all fields and Try again.");
            alert.setContentText("Note: Password must also be more than 8"
                    + " characters");
            Optional<ButtonType> result = alert.showAndWait();
            //Ask user to try again
            if(result.get() == ButtonType.OK)
            {
                //Reset fields
                usernameField.setText("Enter Username*");
                PasswordLabel.setText("Enter Password*");
                passwordField.clear();
            }
        }
        else
        {
            //If validation is successful, then transition to the Server page
            //and create a default user 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Server.fxml"));
            loader.load();
            User user = new User("John", "Smith", "JSmith@gmail.com", 
            usernameField.getText(),passwordField.getText(), 
            passwordField.getText(), Color.GREEN,
                    "Dark","Green");
            //Create user 
            ServerController serverController = loader.getController();
            serverController.setUser(user);
            //Set user details in the server page
            Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        
    }

    /***************************************************
     * Purpose: Clear the username field
     * @param event 
     */
    @FXML
    private void clickUsernameField(MouseEvent event) {

        usernameField.clear();
    }
   
    /***************************************************
     * Purpose: Clear the password field and label
     * @param event 
     */

    @FXML
    void ClearPassword(MouseEvent event) {
        passwordField.clear();
        PasswordLabel.setText("");
    }

    
}
