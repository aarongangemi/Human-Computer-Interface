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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/*********************************************
 * Purpose: The controller for the script controller
 * Author: Aaron Gangemi
 */
public class ServerController implements Initializable{
    
    @FXML
    private TableView<Server> ServerTable;

    @FXML
    private TableColumn<Server, String> Server;
    
    private User user;
    @FXML
    private Text userText;
    
    @FXML
    private Text UsernameText;

    @FXML
    private TableColumn<ConnectedUsers, String> ConnectedUserList;  
    
        @FXML
    private TableView<ConnectedUsers> UserTable;

    @FXML
    private Label ServerNameText;

    @FXML
    private TextField MessageField;

    @FXML
    private TableView<Channel> ChannelTable;

    @FXML
    private TableColumn<Channel, String> ChannelColumn;
    
    @FXML
    private TextField SearchForUserField;

    @FXML
    private Label ChannelNameText;
    
    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane rightPane;

    /********************************************************
     * Purpose: Delete Server from table
     * @param event 
     */
        @FXML
    void DeleteServer(ActionEvent event) {
        if(ServerTable.getSelectionModel().getSelectedItem() != null)
        {
            ServerTable.getItems().removeAll(
                    ServerTable.getSelectionModel().getSelectedItem());
        }
    }
    
    /*******************************************************
     * Purpose: If the user clicks on the help icon, an alert will be displayed 
     * stating the help section
     * @param event
     * @throws URISyntaxException
     * @throws IOException 
     */
    @FXML
    void HelpIconClicked(MouseEvent event) throws URISyntaxException, 
                                                    IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Help Section");
        alert.setHeaderText("This is a help section for Weechat");
        Optional<ButtonType> result = alert.showAndWait();
        //Display alert and wait for response
    }

    
    /*************************************************
     * Purpose: If the user clicks add server, they will be redirected to the 
     * add server page in which they will be able to enter a server name and URL
     * @param event
     * @throws IOException 
     */
    @FXML
    void ClickAddServer(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddServer.fxml"));
        loader.load();
        AddServerController addServerController = loader.getController();
        addServerController.setUser(user);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    /***************************************************************
     * Purpose: If the user clicks the attachment icon, open the file explorer 
     * then the user selects a file and the filename is displayed in chat
     * @param event
     * @throws IOException 
     */
    @FXML
    void AttachementIconClicked(MouseEvent event) throws IOException
    {
        FileChooser fileExplorer = new FileChooser();
        //Open file explorer
        fileExplorer.getExtensionFilters().add(new 
                 ExtensionFilter("All Files","*" ));
        //All files allowed for selection
        File file = fileExplorer.showOpenDialog(null);
        if(file != null && file.exists())//Check selected file exists
        {
            Date date = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //Format date and time
            userText.setText(UsernameText.getText() + " | " + 
                    timeFormat.format(date) + " | " + 
                    dateFormat.format(date) + " | " + file.getCanonicalPath() 
                    + " was uploaded\n");
            //Set text
        }
    }
    


    /*********************************************    
     * Purpose: Clear message field on click
     * @param event 
     */
    @FXML
    void ClickMessageField(MouseEvent event) 
    {
        MessageField.clear();
    }


    /**************************************************
     * Purpose: If the username icon is clicked, transition to user information
     * page
     * @param event
     * @throws IOException 
     */
    @FXML
    void ClickUsernameIcon(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UserInformation.fxml"));
        //Load user information page
        loader.load();
        UserInformationController controller = loader.getController();
        controller.setUser(user);
        //Set user information page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**************************************************
     * Purpose: if emoji icon is clicked, display ":)"
     * @param event 
     */
    @FXML
    void EmojiIconClicked(MouseEvent event) {
        Date date = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //Format date and time
        userText.setText(UsernameText.getText() + " | " +
                    timeFormat.format(date) + " | " + 
                    dateFormat.format(date) + ":" + " :)");
    }

    /*************************************************************
     * Purpose: If the user clicks the exit icon, ask for confirmation on 
     * whether or not they would like to exit
     * @param event 
     */
    @FXML
    void ExitIconClicked(MouseEvent event) {
        //Create alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Weechat");
        alert.setHeaderText("Are you sure you want to exit Weechat");
        //Add cancel button
        ButtonType confirmButton = new ButtonType("Don't Exit", 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = alert.showAndWait();
        //Wait for response
        if(result.get() == ButtonType.OK)
        {
            Node node = (Node) event.getSource();
            final Stage stage = (Stage) node.getScene().getWindow();
            stage.close(); 
            //Exit application
        }
    }

    /*****************************************************
     * Purpose: if the image icon is clicked, display the file path in text
     * to represent the message is sent
     * @param event
     * @throws IOException 
     */
    @FXML
    void ImageIconClicked(MouseEvent event) throws IOException {
        FileChooser fileExplorer = new FileChooser();
        fileExplorer.getExtensionFilters().add(new 
                    ExtensionFilter("Image Files", "*.jpg", "*.png"));
        //Open file explorer
        //Allow image files to be selected
        File file = fileExplorer.showOpenDialog(null);
        if(file != null && file.exists())
        {
            Date date = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
            //Format date and time
            userText.setText(UsernameText.getText() + " | " + 
                    timeFormat.format(date) + " | " + 
                    dateFormat.format(date) + " | " + file.getCanonicalPath() 
                    + " was uploaded\n");
            //Set text
        }
    }

    /******************************************************
     * Purpose: When the user clicks logout, create an alert for confirmation
     * on whether or not to logout
     * @param event
     * @throws IOException 
     */
    @FXML
    void LogoutIconClicked(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Create alert
        alert.setTitle("Exit Weechat");
        alert.setHeaderText("Are you sure you want to Logout of Weechat");
        ButtonType confirmButton = new ButtonType("Don't Logout",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = alert.showAndWait();
        //Wait on response
        if(result.get() == ButtonType.OK)
        {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            //If the user confirms they would like to logout, then logout
            Scene scene = new Scene(root);
            Stage stage = (Stage)
                    ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /******************************************************************
     * Purpose: If the user clicks on the message icon, display the 
     * message page
     * @param event
     * @throws IOException 
     */
    @FXML
    void MessageIconClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DirectMessages.fxml"));
        //Load the direct messages page
        loader.load();
        DMController controller = loader.getController();
        controller.setUser(user);
        controller.setRecipientName("JohnConWon");
        //Set user details and recipient name
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /*************************************************
     * Purpose: If the script icon is clicked, load the script manager.
     * @param event
     * @throws IOException 
     */
    @FXML
    void ScriptIconClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ScriptManager.fxml"));
        //Load script manager
        loader.load();
        ScriptManagerController controller = loader.getController();
        controller.setUser(user);
        //set user data
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /***************************************************
     * Purpose: Clear user field
     * @param event 
     */
    @FXML
    void SearchForUser(MouseEvent event) {
        SearchForUserField.clear();
    }

    /***************************************************
     * Purpose: If settings icon is clicked, load options and settings page
     * @param event
     * @throws IOException 
     */
    @FXML
    void SettingsIconClicked(MouseEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader();
        //Load options and settings page
        loader.setLocation(getClass().getResource("OptionsAndSettings.fxml"));
        loader.load();
        OptionsAndSettingsController controller = loader.getController();
        //Load user data to page
        controller.setUser(user);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /*****************************************************
     * Purpose: if the user selects the back button, go back to the options and
     * settings page
     * @param event
     * @throws IOException 
     */
    @FXML
    void goBack(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OptionsAndSettings.fxml"));
        //Load options and settings page
        loader.load();
        OptionsAndSettingsController controller = loader.getController();
        controller.setUser(user);
        //load user page on options and settings page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    /***************************************************
     * Purpose: set text to inputted text
     * @param event 
     */
        @FXML
    void SendMessage(ActionEvent event) {
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        userText.setText(user.getUsername() + " | " + 
                timeFormat.format(date) + " | " + dateFormat.format(date) 
                + " | " +  MessageField.getText());
    }

    /*************************************************
     * Purpose: Set user details for this page
     * @param user 
     */
    public void setUser(User user)
    {
        this.user = user;
        UsernameText.setText(this.user.getUsername());
        //Set username text
        if(user.getBackgroundColour().equals("Light"))
        {
            leftPane.setStyle("-fx-background-color:  grey");
            rightPane.setStyle("-fx-background-color:  grey");
            //If user selects light, change to grey background
            
        }
        else if(user.getBackgroundColour().equals("Dark"))
        {
            leftPane.setStyle("-fx-background-color:  #00283D");
            rightPane.setStyle("-fx-background-color:  #00283D");
            //If user selects dark, change to dark background
        }
        UsernameText.setFill(user.getUsernameColour());
        Server.setCellValueFactory(new 
                                    PropertyValueFactory<Server, String>
                                    ("ServerName"));
        if(this.user.getServerList().isEmpty())
        {
            this.user.getServerList().add(new Server("Gaming Server"));
            this.user.getServerList().add(new Server("Soccer Server"));
            this.user.getServerList().add(new Server("Chat Server"));
        }
        ServerTable.setItems(this.user.getServerList());
        
        
        //Change username color
        
    }
    

    @FXML
    void ChangeServerName(MouseEvent event) {
        ServerNameText.setText(
             ServerTable.getSelectionModel().getSelectedItem().getServerName());
    }
    
        @FXML
    void ChangeChannelName(MouseEvent event) {
        ChannelNameText.setText(
           ChannelTable.getSelectionModel().getSelectedItem().getChannelName());
    }
    
    /***************************************************
     * Purpose: Add Server to the server table
     * @param text 
     */
    public void addServerToTable(String serverName)
    {
        user.getServerList().add(new Server(serverName));
        ServerTable.setItems(user.getServerList());
        user.setServerList(user.getServerList());
    }
    
        @FXML
    void DirectMessageForConnectedUser(MouseEvent event) throws IOException {
        if(event.getClickCount() >= 2)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("DirectMessages.fxml"));
            loader.load();
            //Load direct messages page
            DMController controller = loader.getController();
            controller.setUser(this.user);
            //Set user and recipient data
            controller.setRecipientName(
                    UserTable.getSelectionModel().getSelectedItem().getName());
            Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<Channel> channelList = FXCollections.observableArrayList();
        ObservableList<ConnectedUsers> connectedUserList =
                FXCollections.observableArrayList();
        ChannelColumn.setCellValueFactory(new 
                        PropertyValueFactory<Channel, String>("ChannelName"));
        channelList.add(new Channel("MyFirstChannel"));
        channelList.add(new Channel("All my friends-Channel"));
        channelList.add(new Channel("Channel 3"));
        ChannelTable.setItems(channelList);
        ConnectedUserList.setCellValueFactory(new 
                        PropertyValueFactory<ConnectedUsers, String>("name"));
        connectedUserList.add(new ConnectedUsers("JohnConWon"));
        connectedUserList.add(new ConnectedUsers("SamisTheMan"));
        connectedUserList.add(new ConnectedUsers("AaronG"));
        UserTable.setItems(connectedUserList);
        
    }
    
    
}
