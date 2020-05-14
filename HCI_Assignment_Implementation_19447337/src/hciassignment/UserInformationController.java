/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hciassignment;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Purpose: Controller for interface of user information
 *
 * Author: Aaron Gangemi
 */
public class UserInformationController{

    
    @FXML
    private Text EditIconText;
    @FXML
    private Text UsernameText;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField LastNameField;
    @FXML
    private TextField UsernameField;
    @FXML
    private TextField EmailField;
    @FXML
    private ComboBox<String> usernameColorList;
    private User user;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane rightPane;
    /********************************************************
     * Purpose: Proceed to the options and settings page if the user clicks 
     * options and settings
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickOptionsAndSettings(MouseEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OptionsAndSettings.fxml"));
        //Load options and settings page
        loader.load();
        OptionsAndSettingsController controller = loader.getController();
        controller.setUser(user);
        //Set user details on options and settings page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /*****************************************************
     * Purpose: If the user clicks script manager, proceed to the script manager
     * page
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickScriptManager(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ScriptManager.fxml"));
        //Load script manager page
        loader.load();
        ScriptManagerController controller = loader.getController();
        controller.setUser(user);
        //Set user details on script manager page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    /************************************************************
     * Purpose: If the user clicks the Exit Weechat icon, display an alert
     * confirming whether they would like to exit weechat
     * @param event 
     */
    @FXML
    private void clickExitWeeChat(MouseEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        //Display alert and details of exit alert
        alert.setTitle("Exit Weechat");
        alert.setHeaderText("Are you sure you want to exit Weechat");
        ButtonType confirmButton = new ButtonType("Don't Exit", 
                                                  ButtonData.CANCEL_CLOSE);
        //Add cancel button
        Optional<ButtonType> result = alert.showAndWait();
        //Wait for response
        if(result.get() == ButtonType.OK)
        {
            Node node = (Node) event.getSource();
            final Stage stage = (Stage) node.getScene().getWindow();
            stage.close(); 
            //If they decide to close, exit Weechat
        }
    }

    /************************************************************
     * Purpose: if the user clicks logout, display a confirmation alert
     * confirming whether the user wishes to logout
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickLogout(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Display confirmation alert
        alert.setTitle("Exit Weechat");
        alert.setHeaderText("Are you sure you want to Logout of Weechat");
        ButtonType confirmButton = new ButtonType("Don't Logout", 
                        ButtonBar.ButtonData.CANCEL_CLOSE);
        //Add cancel button and wait for response
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
            //If user decides to logout, transition to login page
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)
                    ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }


    /*********************************************************
     * Purpose: if the user clicks the server icon, proceed to server page
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickServerIcon(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Server.fxml"));
        //Load server page
        loader.load();
        ServerController serverController = loader.getController();
        serverController.setUser(user);
        //Set user details in server page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /********************************************************
     * Purpose: If the user clicks the message icon, load the direct messages
     * page
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clickMessageIcon(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DirectMessages.fxml"));
        //Load the messages page
        loader.load();
        DMController controller = loader.getController();
        controller.setUser(user);
        controller.setRecipientName("JohnConWon");
        //Set user and recipient details
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /****************************************************
     * Purpose: if the user has selected edit icon, they will be able to edit
     * the fields. If they click the first name field, it will be cleared.
     * @param event 
     */
    @FXML
    private void ClickFirstNameFIeld(MouseEvent event) {
        if(FirstNameField.isEditable())
            //Check isEditable
        {
            FirstNameField.clear();
        }
    }

    /*****************************************************
     * Purpose: If the edit icon has been clicked, the fields are editable.
     * From here, if the user clicks on the last name field, the field will be 
     * cleared
     * @param event 
     */
    @FXML
    private void ClickLastNameField(MouseEvent event) {
        if(LastNameField.isEditable())
        {
            LastNameField.clear();
        }
    }

    /*****************************************************
     * Purpose: If the edit icon has been clicked, the fields are editable.
     * From here, if the user clicks on the username field, the field will be 
     * cleared
     * @param event 
     */
    @FXML
    private void ClickUsernameField(MouseEvent event) {
        if(UsernameField.isEditable())
        {
            UsernameField.clear();
        }
    }

    /*****************************************************
     * Purpose: If the edit icon has been clicked, the fields are editable.
     * From here, if the user clicks on the email field, the field will be 
     * cleared
     * @param event 
     */
    @FXML
    private void ClickEmailField(MouseEvent event) {
        if(EditIconText.getText().equals("Click to Stop Editing"))
        {
            EmailField.clear();
        }
    }

     /*****************************************************
     * Purpose: If the user has clicked the edit icon, allow the user to edit
     * the fields. However, if it has already been clicked and the user wishes
     * to lock the field, then lock the fields by setting them to not editable
     * and change the text.
     * @param event 
     */
    @FXML
    void ClickEditIcon(MouseEvent event) {
        if(EditIconText.getText().equals("Click to Stop Editing"))
        {
            EditIconText.setText("Edit Info");//Change text
            editFirstName();//get firstname text
            editLastName();//get lastname text
            editEmailField();//get email text
            editUsername();//get username text
            FirstNameField.setEditable(false); 
            //disallow fields to be further edited                                   
            LastNameField.setEditable(false);
            EmailField.setEditable(false);
            UsernameField.setEditable(false);
        }
        else if(EditIconText.getText().equals("Edit Info"))
        {
            //If the user decides to edit info
            EditIconText.setText("Click to Stop Editing");
            //Set text and change fields to be edited
            FirstNameField.setEditable(true);
            LastNameField.setEditable(true);
            EmailField.setEditable(true);
            UsernameField.setEditable(true);
        }
    }
    
    /********************************************************
     * Purpose: if the user selects delete account, display a confirmation 
     * and transition to login page
     * @param event
     * @throws IOException 
     */
    @FXML
    private void ClickDeleteAccount(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Display alert asking to exit weechat
        alert.setTitle("Exit Weechat");
        alert.setHeaderText("Are you sure you want to delete your account?");
        //Add cancel button
        ButtonType confirmButton = new ButtonType("Don't Delete",
                                            ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = alert.showAndWait();
        //Wait for response
        if(result.get() == ButtonType.OK)
        {
            //Display login page if user confirms
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)
                    ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /*********************************************************
     * Purpose: Display list options for selecting username color
     * @param event 
     */
    @FXML
    private void displayList(MouseEvent event) {
        String[] colors = {"Green", "Blue", "Orange", "Red", "Yellow","White"};
        usernameColorList.setItems(FXCollections.observableArrayList(colors));
    }

    /*********************************************************
     * Purpose: Change the username color and the background of the color list
     * when an option has been selected.
     * @param event 
     */
    @FXML
    private void changeColor(ActionEvent event) {
        try{
        switch(usernameColorList.getValue()){
            case "Green":
                //Change username color and combo box color
                usernameColorList.setStyle("-fx-background-color:  Green");
                user.setUsernameColour(Color.GREEN);
                user.setUsernameColorText("Green");
                break;
            case "Blue":
                //Change username color and combo box color
                usernameColorList.setStyle("-fx-background-color:  Blue");
                user.setUsernameColour(Color.BLUE);
                user.setUsernameColorText("Blue");
                break;
            case "Orange":
                //Change username color and combo box color
                usernameColorList.setStyle("-fx-background-color:  Orange");
                user.setUsernameColour(Color.ORANGE);
                user.setUsernameColorText("Orange");
                break;
            case "Red":
                //Change username color and combo box color
                usernameColorList.setStyle("-fx-background-color:  Red");
                user.setUsernameColour(Color.RED);
                user.setUsernameColorText("Red");
                break;    
            case "Yellow":
                //Change username color and combo box color
                usernameColorList.setStyle("-fx-background-color:  Yellow");
                user.setUsernameColour(Color.YELLOW);
                user.setUsernameColorText("Yellow");
                break;    
            case "White":
                //Change username color and combo box color
                usernameColorList.setStyle("-fx-background-color:  White");
                user.setUsernameColour(Color.WHITE);
                user.setUsernameColorText("White");
                break;    
        }
        UsernameText.setFill(user.getUsernameColour());
        //Change username colour
    }
        catch(NullPointerException e){
            System.out.println("Option Changing");
            //Catch Null pointer so program does not crash when user is 
            //in between choosing
        }
    }

    /*********************************************************
     * Purpose: If the user selects the back button, go back to the server page
     * @param event
     * @throws IOException 
     */
    @FXML
    private void goBack(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Server.fxml"));
        //Load server page
        loader.load();
        ServerController serverController = loader.getController();
        serverController.setUser(this.user);
        //Set user data on server page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    /*************************************************
     * Purpose: Set data on the user information page
     * @param user 
     */
    public void setUser(User user)
    {
        this.user = user;
        UsernameText.setText(this.user.getUsername());//Set username
        UsernameField.setText(this.user.getUsername()); //Set password
        FirstNameField.setText(this.user.getFirstName()); //set firstname
        LastNameField.setText(this.user.getLastName());//Set lastname
        EmailField.setText(this.user.getEmailAddress());//set email
        usernameColorList.setStyle("-fx-background-color: " 
                + user.getUsernameColorText()); //set username color list
        if(user.getBackgroundColour().equals("Light")) 
        {
            leftPane.setStyle("-fx-background-color: Grey");
            rightPane.setStyle("-fx-background-color: Grey");
            //Change current interface color to light if selected
            
        }
        else if(user.getBackgroundColour().equals("Dark"))
        {
            leftPane.setStyle("-fx-background-color: #00283D");
            rightPane.setStyle("-fx-background-color: #00283D");
            //Change current interface colour if dark is selected
        }
        usernameColorList.setValue(user.getUsernameColorText()); 
        //set preselected username color text in combo box
        UsernameText.setFill(user.getUsernameColour());
        //Change username color
    }
    /**********************************************************
     * Purpose: Set first name text before user locks field
     */
    private void editFirstName()
    {
        if(FirstNameField.getText().isEmpty())
        {
            FirstNameField.setText("<First Name Here>");
        }
        else
        {
            user.setUsername(FirstNameField.getText());
        }    
    }
     /**********************************************************
     * Purpose: Set last name text before user locks field
     */
    private void editLastName()
    {
        if(LastNameField.getText().isEmpty())
        {
            LastNameField.setText("<Last Name Here>");
        }
        else
        {
            user.setLastName(LastNameField.getText());
        }
    }
     /**********************************************************
     * Purpose: Set email text before user locks field
     */
    private void editEmailField()
    {
        if(EmailField.getText().isEmpty())
        {
            EmailField.setText("<Email Here>");
        }
        else
        {
            user.setEmailAddress(EmailField.getText());
        }
    }
     /**********************************************************
     * Purpose: Set username text before user locks field
     */
    private void editUsername()
    {
        if(UsernameField.getText().isEmpty())
        {
            UsernameField.setText("<Username Here>");
        }
        else
        {
            user.setUsername(UsernameField.getText());
        }
        UsernameText.setText(UsernameField.getText());
    }
    
    
}
