package hciassignment;


import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**********************************************
 * Purpose: Create Account Controller
 * Author: Aaron Gangemi
 * Date Modified: 08/05/2020
 */
public class CreateAccountController {

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private CheckBox TCBox;
        
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;
    @FXML
    private ComboBox<String> usernameColorList;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private ComboBox<String> backgroundColorList;
    private Color color;
    private String colorText;
    /****************************************************
     * Purpose: If the user clicks "Create an Account", the function firstly 
     * set the user preferences for background color and color theme. It will
     * then check that the user data has been filled in correctly doing some 
     * basic validation to check user fields are not empty. If fields are empty,
     * then they will be alerted and have to try again. However, if they are
     * successful, they will proceed to the main server page.
     * Reference: 
     * Email regex taken from:
     * https://www.tutorialspoint.com/validate-email-address-in-java
     * Author: Arushi, Published: 19 Feb 2019
     * Date Accessed 07/05/2020
     * @param event
     * @throws IOException 
     */
    @FXML
    void ClickCreateAccount(ActionEvent event) throws IOException {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(backgroundColorList.getValue() == null)
        {
            backgroundColorList.setValue("Dark");
            //If no background color was selectged
        }
        if(color == null || colorText.isEmpty())
        {
            color = Color.GREEN;
            colorText = "Green";
            //if no username color was selected
        }
        if(usernameField.getText().isEmpty() || 
           firstNameField.getText().isEmpty() ||
           lastNameField.getText().isEmpty() ||
           emailField.getText().isEmpty() ||
           passwordField.getText().isEmpty() ||
           confirmPasswordField.getText().isEmpty() || 
           !passwordField.getText().equals(confirmPasswordField.getText()) ||
           passwordField.getText().length() < 8 || 
           !emailField.getText().contains("@") || 
           !emailField.getText().matches(regex) ||
            !TCBox.isSelected())
        //If any fields are left empty or the passwords do not match
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);//Create Alert
            alert.setTitle("Invalid Input"); //Set alert details
            alert.setHeaderText("Please fill out all fields and try again. "
                    + "NOTE: passwords need to match to proceed. T & C Box "
                    + "must also be ticked");
            alert.setContentText("Email must be valid format and password must"
                    + " be less than 8 character");
            Optional<ButtonType> result = alert.showAndWait();
            //Display alert and wait for response
            if(result.get() == ButtonType.OK) //If user selects okay
            {
                passwordField.clear();
                confirmPasswordField.clear();
                //Reset all fields to initial titles and user can try again
            }
        }else{//If all fields are valid
            User user = new User(firstNameField.getText(), 
                            lastNameField.getText(),emailField.getText(), 
                            usernameField.getText(), passwordField.getText(), 
                            confirmPasswordField.getText(),color, 
                            backgroundColorList.getValue(), colorText);
            //Create user to be passed around
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Server.fxml"));
            //Go to main server page
            loader.load();
            ServerController serverController = loader.getController();
            serverController.setUser(user);
            //Set user data that was entered in valid fields
            Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    
    
    /****************************************************
     * Purpose: Change username color and change background of combo box
     * @param event 
     */
    @FXML
    void changeColor(ActionEvent event) {
        try{
        switch(usernameColorList.getValue()){
            case "Green":
                //Change to Color Green
                usernameColorList.setStyle("-fx-background-color:  Green");
                //Set Background
                color = Color.GREEN;
                colorText = "Green";
                break;
            case "Blue":
                //Change color to Blue
                //Change Background
                usernameColorList.setStyle("-fx-background-color:  Blue");
                color = Color.BLUE;
                colorText = "Blue";
                break;
            case "Orange":
                //Change color to Orange
                //Change Background
                usernameColorList.setStyle("-fx-background-color:  Orange");
                color = Color.ORANGE;
                colorText = "Orange";
                break;
            case "Red":
                 //Change color to Red
                //Change Background
                usernameColorList.setStyle("-fx-background-color:  Red");
                color = Color.RED;
                colorText = "red";
                break;    
            case "Yellow":
                //Change color to Yellow
                //Change Background
                usernameColorList.setStyle("-fx-background-color:  Yellow");
                color = Color.YELLOW;
                colorText = "yellow";
                break;    
            case "White":
                //Change color to Orange
                //Change Background
                usernameColorList.setStyle("-fx-background-color:  White");
                color = Color.WHITE;
                colorText = "white";
                break;    
        }
    }
        catch(NullPointerException e){
            System.out.println("Option Changing");
            //Null Pointer exception is caught because when the user is choosing
            //an option, effectively there is option is Null until selected
        }
    }

    /*****************************************************
     * Purpose: Set the list of username colors
     * @param event 
     */
    @FXML
    void displayList(MouseEvent event) {
        String[] colors = {"Green", "Blue", "Orange", "Red", "Yellow","White"};
        usernameColorList.setItems(FXCollections.observableArrayList(colors));
    }
    /*********************************************************
     * Purpose: Clear email field on click
     * @param event 
     */
    @FXML
    void clickEmail(MouseEvent event) {
        emailField.clear();
    }
    /*********************************************************
     * Purpose: Clear first name field on click
     * @param event 
     */
    @FXML
    void clickFirstName(MouseEvent event) {
        firstNameField.clear();
    }
    /*********************************************************
     * Purpose: Clear last name field on click
     * @param event 
     */
    @FXML
    void clickLastName(MouseEvent event) {
        lastNameField.clear();
    }
    /*********************************************************
     * Purpose: Clear username field on click
     * @param event 
     */
    @FXML
    void clickuserName(MouseEvent event) {
        usernameField.clear();
    }
    /*********************************************************
     * Purpose: If the user selects the back button, go back to the Login page
     * @param event 
     */
    @FXML
    void goBack(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }    
    
    /*********************************************************
     * Purpose: If the user clicks the confirm password field, then clear the 
     * field
     * @param event 
     */
    @FXML
    void ClearConfirm(MouseEvent event) {
        confirmPasswordLabel.setText("");
        confirmPasswordField.clear();
    }

    /********************************************************
     * Purpose: if the user selects the password field then clear the field
     * @param event 
     */
    @FXML
    void ClearPassword(MouseEvent event) {
        passwordField.clear();
        passwordLabel.setText("");
    }
    /*********************************************************
     * Purpose: Set the options available for choosing in the background list.
     * @param event 
     */
    @FXML
    private void displayBgList(MouseEvent event) {
        String[] colors = {"Dark", "Light"};
        backgroundColorList.setItems(FXCollections.observableArrayList(colors));
    }

    /*********************************************************
     * Purpose: Change the background color on click of option
     * @param event 
     */
    @FXML
    private void changeBgColor(ActionEvent event) {
        try{
             if(backgroundColorList.getValue().equals("Light"))
                 //If user wants light theme
             {
                backgroundColorList.setStyle("-fx-background-color:  White");
                anchorPane.setStyle("-fx-background-color:  grey");
                //Change background color of application
                //User takes backgroundColor.getValue();
             }
             else
             {
                 //If user wants dark theme
                backgroundColorList.setStyle("-fx-background-color:  #3300FF");
                anchorPane.setStyle("-fx-background-color:  #00283D");
             }
        }
        catch(NullPointerException e){
            System.out.println("Option Changing");
            //Catches null pointer so user can still resume while choosing
        }
    }

}
