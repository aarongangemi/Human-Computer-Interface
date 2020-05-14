/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hciassignment;

import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Purpose: Options and Settings interface controller
 * Author: Aaron Gangemi
 * Date Modified: 08/05/2020
 */
public class OptionsAndSettingsController {

    @FXML
    private TextArea FeedbackField;
    @FXML
    private ComboBox<String> TimeZoneList;
    @FXML
    private Text UsernameText;
    @FXML
    private ComboBox<Integer> FontSizeList;
    @FXML
    private ComboBox<String> BackgroundColorList;
    @FXML
    private ComboBox<String> TextColorList;
    private User user;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane rightPane;
    
    /****************************************************
     * Purpose: Clear the feedback field
     * @param event 
     */
    @FXML
    void ClearFeedbackField(MouseEvent event) {
        FeedbackField.clear();
    }
    /*****************************************************
     * Purpose: Clear the feedback field and display and show an alert 
     * stating that feedback has been sent to Weechat
     * @param event 
     */
    @FXML
    void SubmitFeedback(ActionEvent event) {
        FeedbackField.clear();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Displat alert
        alert.setTitle("Feedback Sent");
        alert.setHeaderText("Thank you for Submitting your Feedback");
        alert.setContentText("Your feedback has been sent to Weechat");
        Optional<ButtonType> result = alert.showAndWait();
        //Wait till user selects okay
    }

    /*******************************************************
     * Purpose: When the user clicks the username icon in the top left corner,
     * they will be redirected to the user information page
     * @param event
     * @throws IOException 
     */
    @FXML
    private void ClickUserinformation(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UserInformation.fxml"));
        //Go to user information page
        loader.load();
        UserInformationController controller = loader.getController();
        controller.setUser(user);
        //Set user data on info page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    
    /******************************************************
     * Purpose: If the user clicks the server icon, then proceed to the server 
     * page
     * @param event
     * @throws IOException 
     */
    @FXML
    void ClickServerIcon(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Server.fxml"));
        //Go to server page
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
    
    /**************************************************
     * Purpose: When the user clicks script manager, go to the script manager
     * interface
     * @param event
     * @throws IOException 
     */
    @FXML
    private void ClickScriptManager(MouseEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ScriptManager.fxml"));
        //Go to script manager location
        loader.load();
        ScriptManagerController controller = loader.getController();
        controller.setUser(user);
        //Set user details in script pge
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /*************************************************
     * Purpose: If the user clicks the exit icon, they will be prompted with an 
     * alert icon to exit the program.
     * @param event 
     *****************************************/
    @FXML
    private void ClickExitWeechat(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Weechat");
        alert.setHeaderText("Are you sure you want to exit Weechat");
        //Display alert
        ButtonType confirmButton = new ButtonType("Don't Exit",
                            ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = alert.showAndWait();
        //Wait till user selects cancel or ok
        if(result.get() == ButtonType.OK)
        {
            Node node = (Node) event.getSource();
            final Stage stage = (Stage) node.getScene().getWindow();
            stage.close(); 
            //If user selects okay, close program
        }
    }

    /**************************************************
     * Purpose: When the user clicks login, display an alert confirming whether
     * or not the user would like to logout. 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void ClickLogout(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Display alert
        alert.setTitle("Exit Weechat");
        alert.setHeaderText("Are you sure you want to Logout of Weechat");
        ButtonType confirmButton = new ButtonType("Don't Logout",
                                ButtonBar.ButtonData.CANCEL_CLOSE);
        //Add close option
        Optional<ButtonType> result = alert.showAndWait();
        //Wait for response
        if(result.get() == ButtonType.OK)
        {
            //Go to login interface 
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    
    /**********************************************************
     * Purpose: When message icon is clicked, transition to the Direct
     * Messages interface
     * @param event
     * @throws IOException 
     */
    @FXML
    void ClickMessageIcon(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DirectMessages.fxml"));
        //Go to Direct Messages page
        loader.load();
        DMController controller = loader.getController();
        controller.setUser(user);
        controller.setRecipientName("JohnConWon");
        //Set user details and recipient
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /********************************************
     * Purpose: Create font list options and add items to combo box
     * @param event 
     */
    @FXML
    private void displayFontList(MouseEvent event) {
        Integer[] fontArray = new Integer[6];
        int count = 0;
        for(int i = 8; i < 20; i=i+2){ //Create numbers
            fontArray[count] = i;
            count++;
        }
       FontSizeList.setItems(FXCollections.observableArrayList(fontArray));
       
    }


    /*****************************************************
     * Purpose: Set background color on click of combo box option
     * @param event 
     */
    @FXML
    private void ChangeTextColor(ActionEvent event) {
        try{
        switch(TextColorList.getValue()){
            case "Green":
                //Change color to green
                TextColorList.setStyle("-fx-background-color:  Green");
                break;
            case "Blue":
                //Change color to blue
                TextColorList.setStyle("-fx-background-color:  Blue");
                break;
            case "Orange":
                //Change color to Orange
                TextColorList.setStyle("-fx-background-color:  Orange");
                break;
            case "Red":
                //Change color to red
                TextColorList.setStyle("-fx-background-color:  Red");
                break;    
            case "Yellow":
                //Change color to yellow
                TextColorList.setStyle("-fx-background-color:  Yellow");
                break;    
            case "White":
                //Change color to white
                TextColorList.setStyle("-fx-background-color:  White");
                break;
    }}catch(NullPointerException e){
        System.out.println("Option Changing");
        //Catch null pointer so exception is not thrown when user is 
        //between selection
    }}

    /***********************************************************
     * Purpose: Set time zone options
     * @param event 
     */
    @FXML
    private void ChangeTime(MouseEvent event) {
        String[] timeZones = {"WA (GMT+8)", "ACT (GMT+10)", "NSW (GMT+10)", 
            "NT (GMT+9.30)", "QLD (GMT+10)", "SA (GMT+9.30)", "TAS (GMT+10)", 
            "VIC (GMT+10)"};
        TimeZoneList.setItems(FXCollections.observableArrayList(timeZones));
    }

    /*******************************************************
     * Purpose: If the user selects the back button, they will be redirected to 
     * the server page 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void goBack(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Server.fxml"));
        //Go to server page
        loader.load();
        ServerController serverController = loader.getController();
        serverController.setUser(user);
        //Sets the user details on the server page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    /***************************************************
     * Purpose: Change the background color of the user interface and current
     * interface
     * @param event 
     */
    
    @FXML
    void ChangeBackgroundColor(ActionEvent event) {
        try
        {
        switch(BackgroundColorList.getValue())
        {
            case "Light": //If user selects light
                BackgroundColorList.setStyle("-fx-background-color:  White");
                //Change list background
                user.setBackgroundColour("Light");
                //Change all other backgrounds
                leftPane.setStyle("-fx-background-color: Grey");
                rightPane.setStyle("-fx-background-color: Grey");
                //Chnage current interface
                break;
            case "Dark": //User selects dark
                BackgroundColorList.setStyle("-fx-background-color:  #3300FF");
                //Change style of backgroundList
                user.setBackgroundColour("Dark");
                //Set background color and change interface
                leftPane.setStyle("-fx-background-color: #00283D");
                rightPane.setStyle("-fx-background-color: #00283D");
                break;
        }
        }
        catch(NullPointerException e)
        {
            System.out.println("Option Changing");
            //Catch Null Pointer so when user is selecting, program does not
            //craah
        }
    }


    @FXML
    void DisplayBackgroundList(MouseEvent event) {
        String[] colors = {"Light", "Dark"};
        BackgroundColorList.setItems(FXCollections.observableArrayList(colors));
    }

    @FXML
    void DisplayTextColorList(MouseEvent event) {
        String[] colors = {"Green", "Blue", "Orange", "Red", "Yellow","White"};
        TextColorList.setItems(FXCollections.observableArrayList(colors));
    }
    
    public void setUser(User user)
    {
        this.user = user;
        UsernameText.setText(this.user.getUsername());
        if(user.getBackgroundColour().equals("Light"))
        {
            leftPane.setStyle("-fx-background-color: Grey");
            rightPane.setStyle("-fx-background-color: Grey");
            BackgroundColorList.setStyle("-fx-background-color: White");
        }
        else if(user.getBackgroundColour().equals("Dark"))
        {
            leftPane.setStyle("-fx-background-color: #00283D");
            rightPane.setStyle("-fx-background-color: #00283D");
            BackgroundColorList.setStyle("-fx-background-color: #3300FF");
        }
        UsernameText.setFill(user.getUsernameColour());
        BackgroundColorList.setValue(user.getBackgroundColour());
        
    }
}
