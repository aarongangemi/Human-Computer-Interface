package hciassignment;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/********************************************
 * Purpose: Direct Messages Controller
 * Author: Aaron Gangemi
 * Date Modified: 08/05/2020
 */
public class DMController implements Initializable{


    @FXML
    private Text UsernameText;
    
    @FXML
    private Text SentMessage;

    @FXML
    private Label RecipientNameText;

    @FXML
    private TextField MessageField;
    
    @FXML
    private TextField SearchForUserField;
    
    @FXML
    private Text RecipientMessage;
    
    private User user;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane rightPane;
    
    @FXML
    private TableView<ConnectedUsers> MessageTable;

    @FXML
    private TableColumn<ConnectedUsers, String> UserList;

    /*********************************************************
     * Purpose: When the user clicks the attachment icon, the file path will
     * be displayed to act as a stub for the file being uploaded to chat.
     * @param event
     * @throws IOException 
     */
    @FXML
    void AttachementIconClicked(MouseEvent event) throws IOException {
        FileChooser fileExplorer = new FileChooser();
        fileExplorer.getExtensionFilters().add(new 
        FileChooser.ExtensionFilter("All Files","*" )); 
        //Set all files as available for choosing in File Explorer
        File file = fileExplorer.showOpenDialog(null);
        //Open file explorer for choosing
        if(file != null && file.exists())
        {
            Date date = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //Get current date and time format
            SentMessage.setText(UsernameText.getText() + " | " + 
                    timeFormat.format(date) + " | " + 
                    dateFormat.format(date) + " | " + file.getCanonicalPath() 
                    + " was uploaded\n");
            //Display attachment file name and time in message
        }
    }
    
  
        /************************************************
     * Purpose: If the username of another user is clicked, the recipient name 
     * and time of sent message will be displayed instead.
     * @param event 
     */
        @FXML
    void ClickUser(MouseEvent event) {
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        //Format the date and time
        //Get currently selected user and set as recipient
        RecipientMessage.setText(
                MessageTable.getSelectionModel().getSelectedItem().getName() 
                        + " | " +  timeFormat.format(date) + " | " + 
                    dateFormat.format(date) + " | " + "I sent this message");
        RecipientNameText.setText(
                MessageTable.getSelectionModel().getSelectedItem().getName());
        //Get currently selected user and set as recipient
        //Display the recipient name and message
    }
    
    
    
    /*****************************************************
     * Purpose: if the user clicks send, display message in chat
     * @param event 
     */
    @FXML
    void SendMessage(ActionEvent event) {
            Date date = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //Format date and time
            SentMessage.setText(UsernameText.getText() + " | " + 
                    timeFormat.format(date) + " | " + 
                    dateFormat.format(date) + " | " + MessageField.getText());
    }

    /*******************************************************
     * Purpose: If the user clicks the message field, clear the text
     * @param event 
     */
    @FXML
    void ClickMessageField(MouseEvent event) {
        MessageField.clear();
    }

    /******************************************************
     * Purpose: if the user clicks the username icon in the top left hand corner
     * then they will proceed to the user information page where there details
     * are displayed.
     * @param event
     * @throws IOException 
     */
    @FXML
    void ClickUsernameIcon(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UserInformation.fxml"));
        //Go to user information page
        loader.load();
        UserInformationController controller = loader.getController();
        controller.setUser(user);
        //Set user details in the user information page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /*******************************************************
     * Purpose: If the user selects the emoji icon, display it ":)"
     * @param event 
     */
    @FXML
    void EmojiIconClicked(MouseEvent event) {
            Date date = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //Format date and time
        SentMessage.setText(UsernameText.getText() + " | " +
                    timeFormat.format(date) + " | " + 
                    dateFormat.format(date) + ":" + " :)");
    }
    
    /******************************************************
     * Purpose: Display an alert if the help icon is clicked
     * @param event
     * @throws URISyntaxException
     * @throws IOException 
     ************************************************/
    @FXML
    void HelpIconClicked(MouseEvent event) throws URISyntaxException,IOException 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Display the alert
        alert.setTitle("Help Section");
        alert.setHeaderText("This is a help section for Weechat");
        //Indicate this is a help menu
        Optional<ButtonType> result = alert.showAndWait();
        //Wait until user responds
    }

    /*************************************************
     * Purpose: If the user clicks the exit icon, they will be prompted with an 
     * alert icon to exit the program.
     * @param event 
     *****************************************/
    @FXML
    void ExitIconClicked(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Weechat");
        alert.setHeaderText("Are you sure you want to exit Weechat");
        //Display alert for exit
        ButtonType confirmButton = new ButtonType("Don't Exit",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        //If user decides not to exit
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
            //If user selects ok, then exit the program
            Node node = (Node) event.getSource();
            final Stage stage = (Stage) node.getScene().getWindow();
            stage.close(); 
        }
    }

    /*****************************************************
     * Purpose: if the user click the image icon, they will be prompted with a
     * file explorer window. The image file location with then be displayed as
     * a stub to represent the image has been uploaded to chat.
     * @param event
     * @throws IOException 
     */
    @FXML
    void ImageIconClicked(MouseEvent event) throws IOException {
        FileChooser fileExplorer = new FileChooser();
        //Open file explorer
        fileExplorer.getExtensionFilters().add(new 
        FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        //Set filters to only allow users to open image files
        File file = fileExplorer.showOpenDialog(null);
        if(file != null && file.exists()) //check file exists
        {
            Date date = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
            //Select date and time format for string
            SentMessage.setText(UsernameText.getText() + " | " + 
                    timeFormat.format(date) + " | " + 
                    dateFormat.format(date) + " | " + file.getCanonicalPath() 
                    + " was uploaded\n");
            //send message
        }
    }

    /**********************************************
     * Purpose: If the user clicks the logout icon, they will be prompted
     * with an alert confirming if they do want to logout. If they confirm,
     * they will be redirected to the login page
     * @param event
     * @throws IOException 
     */
    @FXML
    void LogoutIconClicked(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //Display alert
        alert.setTitle("Exit Weechat");
        alert.setHeaderText("Are you sure you want to Logout of Weechat");
        ButtonType confirmButton = new ButtonType("Don't Logout",
                                            ButtonBar.ButtonData.CANCEL_CLOSE);
        //Allow the user to return to page
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) //User confirms logout
        {
            //Go to login page
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }


    /***************************************************
     * Purpose: If the user clicks the script icon, the will be redirected to
     * the script manager page
     * @param event
     * @throws IOException 
     */
    @FXML
    void ScriptIconClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ScriptManager.fxml"));
        //Go to script manager page
        loader.load();
        ScriptManagerController controller = loader.getController();
        controller.setUser(user);
        //Set user data in script manager page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /*****************************************************
     * Purpose: if the user clicks search for user, clear the field
     * @param event 
     */
    @FXML
    void SearchForUser(MouseEvent event) {
        SearchForUserField.clear();
    }

    /*****************************************************
     * Purpose: If the user clicks the server icon, then proceed to server page
     * @param event
     * @throws IOException 
     */
    @FXML
    void ServerIconClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Server.fxml"));
        //Go to server page
        loader.load();
        ServerController serverController = loader.getController();
        serverController.setUser(user);
        //set user data in server page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /******************************************************
     * Purpose: If the user clicks the settings icon, they will be redirected to
     * the options and settings page. 
     * @param event
     * @throws IOException 
     */
    @FXML
    void SettingsIconClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OptionsAndSettings.fxml"));
        //Go to the options and settings page
        loader.load();
        OptionsAndSettingsController controller = loader.getController();
        controller.setUser(user);
        //Set user data in page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /*********************************************************
     * Purpose: Go back to the server page if the user selects the back arrow
     * @param event
     * @throws IOException 
     */
    @FXML
    void goBack(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Server.fxml"));
        //Go to the server page
        loader.load();
        ServerController serverController = loader.getController();
        serverController.setUser(user);
        //set all user data in server page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**************************************
     * Purpose: Set the recipient name when page is loaded
     * @param username 
     */
    public void setRecipientName(String username)
    {
        RecipientNameText.setText(username);
        //Use to set recipient name before page is loaded
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        //Set previous recipient message
        RecipientMessage.setText(RecipientNameText.getText() + " | " + 
                    timeFormat.format(date) + " | " + 
                    dateFormat.format(date) + " | " + "I sent this message");
    }
    /****************************************************
     * Purpose: Set user details and background details on the DM page
     * @param user 
     *************************************************/
    public void setUser(User user)
    {
        this.user = user;
        UsernameText.setText(this.user.getUsername());
        //Set the username text in the top corner
        if(user.getBackgroundColour().equals("Light")) //If user selects light
        {
            leftPane.setStyle("-fx-background-color:  grey");
            rightPane.setStyle("-fx-background-color:  grey");
            //Set current page to grey background
        }
        else if(user.getBackgroundColour().equals("Dark"))//User selects dark
        {
            leftPane.setStyle("-fx-background-color:#00283D");
            rightPane.setStyle("-fx-background-color:#00283D");
            //Set background to dark
        }
        UsernameText.setFill(user.getUsernameColour());
        //Change username color
    }

    /***************************************************
     * Purpose: Used to initialize direct message user table
     * @param arg0
     * @param arg1 
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
               ObservableList<ConnectedUsers> connectedUserList = 
                       FXCollections.observableArrayList();
               //Create list of connected users
        UserList.setCellValueFactory(new 
                        PropertyValueFactory<ConnectedUsers, String>("name"));
        //Initialise user list column
        connectedUserList.add(new ConnectedUsers("JohnConWon"));
        connectedUserList.add(new ConnectedUsers("SamisTheMan"));
        connectedUserList.add(new ConnectedUsers("AaronG"));
        //Set previously selected and messaged users
        MessageTable.setItems(connectedUserList);
        //Add users to table
    }

    

}