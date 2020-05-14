package hciassignment;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/***********************************************
 * Purpose: The controller for the script manager interface
 * Author: Aaron Gangemi
 * Date Modified: 08/05/2020
 */
public class ScriptManagerController {

    
    @FXML
    private TextField SearchScriptField;
    @FXML
    private Text UsernameText;
    @FXML
    private TextField UploadScriptField;
    private User user;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private TableView<Script> scriptTable;
    @FXML
    private TableColumn<Script, String> ScriptName;
    @FXML
    private TableColumn<Script, LocalDate> ScriptDate;
    @FXML
    private TableColumn<Script, String> ScriptDescription;
    @FXML
    private TableColumn<Script, String> ScriptCommand;
    @FXML
    private TableColumn<?, ?> DeleteColumn;
    
    

    /*********************************************************
     * Purpose: If the user clicks the direct messages icon, then proceed to 
     * the messages page and load the user and recipient data
     * @param event
     * @throws IOException 
     */
    @FXML
    void ClickDMIcon(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DirectMessages.fxml"));
        //Load messages page
        loader.load();
        DMController controller = loader.getController();
        controller.setUser(user);
        //Set user and recipient data
        controller.setRecipientName("JohnConWon");
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**********************************************************
     * Purpose: Display confirmation box to ask user whether they want to
     * exit weechat
     * @param event 
     */
    @FXML
    void ClickExitWeechat(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Display alert
        alert.setTitle("Exit Weechat");
        alert.setHeaderText("Are you sure you want to exit Weechat");
        ButtonType confirmButton = new ButtonType("Don't Exit", 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        //Confirm option to exit or not
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) //Exit program
        {
            Node node = (Node) event.getSource();
            final Stage stage = (Stage) node.getScene().getWindow();
            stage.close(); 
        }
    }
   

    /*************************************************
     * Purpose: Ask for confirmation when the user selects the logout icon
     * @param event
     * @throws IOException 
     */
    @FXML
    void ClickLogout(MouseEvent event) throws IOException {
        //Display confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Weechat");
        alert.setHeaderText("Are you sure you want to Logout of Weechat");
        //Add cancel button
        ButtonType confirmButton = new ButtonType("Don't Logout", 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        //Wait for response
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
            //If user selects okay, go to login
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)
                    ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /*****************************************************
     * Purpose:Transition to the options and settings page if the user selects
     * the options and settings tab
     * @param event
     * @throws IOException 
     */
    @FXML
    void ClickOptionsAndSettings(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OptionsAndSettings.fxml"));
        loader.load();
        //Load options and settings page
        OptionsAndSettingsController controller = loader.getController();
        controller.setUser(user);
        //Set user details
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    /*****************************************************
     * Purpose: When the user selects "Search", open a file explorer and
     * add the file to the script table
     * @param event
     * @throws IOException
     * @throws URISyntaxException 
     */
    @FXML
    void ClickSearchScriptBtn(ActionEvent event) throws IOException,
                                                URISyntaxException {
        //Create alert
        try
        {
            if(!SearchScriptField.getText().isEmpty())
            {
                new URL(SearchScriptField.getText()).toURI();
                TextInputDialog dialog = new TextInputDialog("Command to"
                        + " run script");
                dialog.setTitle("Found script online");
                dialog.setHeaderText("Enter Command to run");
                Optional<String> result = dialog.showAndWait();
                //Wait for response
                if(result.isPresent())
                {
                    //if user selects okay, add to 
                    TextInputDialog dialogDescription = new 
                    TextInputDialog("Script Description");
                    dialogDescription.setTitle("Found script online");
                    dialogDescription.setHeaderText("Enter script description");
                    Optional<String>resultDesc =dialogDescription.showAndWait();
                    if(resultDesc.isPresent())
                    {
                        DateTimeFormatter formatter = 
                                DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        user.getListOfScripts().add(new Script(
                        SearchScriptField.getText(),
                        formatter.format(LocalDate.now()), 
                        dialogDescription.getResult(), 
                        dialog.getResult()));
                        SearchScriptField.setText("Online Script Found");
                        //add script and reload table
                        scriptTable.setItems(user.getListOfScripts());
                        user.setListOfScripts(user.getListOfScripts());
                    }
                }
            }
            else
            {
                
            }
        }
        catch(MalformedURLException e)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invalid URL Found");
            alert.setHeaderText("Unable to search URL");
            alert.setContentText("Please ensure that the URL entered is in a"
                   + " correct format and try again");
            alert.showAndWait();
            //Wait for response
                SearchScriptField.setText("Search Script Online - Enter URL");
        }
    
    }
    
    /****************************************************
     * Purpose: If the search field is clicked, clear the field
     * @param event 
     */
    @FXML
    void ClickSearchField(MouseEvent event) {
        SearchScriptField.clear();
    }
    /*****************************************************
     * Purpose: If the upload field is clicked, clear the text
     * @param event 
     */
    @FXML
    void ClickUploadField(MouseEvent event) {
        UploadScriptField.clear();
    }

    /***********************************************************
     * Purpose: If the user clicks the server icon in the top corner,
     * proceed to the Server page and load the user data
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
        //Set user data
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /***********************************************************
     * Purpose: If the user clicks the upload button, open the file explorer and 
     * let them search for the file to upload to the script table
     * @param event
     * @throws IOException 
     */
    @FXML
    void ClickUploadBtn(ActionEvent event) throws IOException {
        FileChooser fileExplorer = new FileChooser();
        File file = fileExplorer.showOpenDialog(null);
        //Open a file explorer and ask user to select a file
        if(file != null && file.exists())
        {
            UploadScriptField.setText(file.getCanonicalPath());
            //Set the upload field text
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                                                  "dd/MM/yyyy");
            TextInputDialog dialog = new TextInputDialog("Command to"
                        + " run script");
                dialog.setTitle("Found script from File Explorer");
                dialog.setHeaderText("Enter Command to run");
                Optional<String> result = dialog.showAndWait();
                //Wait for response
                if(result.isPresent())
                {
                    TextInputDialog dialogDescription = new 
                    TextInputDialog("Script Description");
                    dialogDescription.setTitle("Found script in explorer");
                    dialogDescription.setHeaderText("Enter script description");
                    Optional<String>resultDesc =dialogDescription.showAndWait();
                    if(resultDesc.isPresent())
                    {
                        //Set the date format for the string
                        //set the image view as the delete button
                        user.getListOfScripts().add(new Script(file.getName(),
                        formatter.format(LocalDate.now()), 
                                dialogDescription.getResult(), 
                                             dialog.getResult()));
                        //Add script to list and set details
                        scriptTable.setItems(user.getListOfScripts());
                        //Reload table
                        user.setListOfScripts(user.getListOfScripts());
                    }
                }
        }
    }

    /*********************************************************
     * Purpose: If the user clicks the user information tab, transition them to 
     * the user information page
     * @param event
     * @throws IOException 
     */
    @FXML
    void ClickUserInformation(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UserInformation.fxml"));
        //Load user information page
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

    /***********************************************************
     * Purpose: if the user selects the back icon, go back to the server page
     * @param event
     * @throws IOException 
     */
    @FXML
    void goBack(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Server.fxml"));
        //Go back to server page
        loader.load();
        ServerController serverController = loader.getController();
        serverController.setUser(user);
        //Set user data
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    /*****************************************************
     * Purpose: Method used to set any required user data for the script manager
     * page including the username in the top corner and the background.
     * Also to initialize a table view on interface load
     * @param user 
     * Reference: https://www.youtube.com/watch?v=uh5R7D_vFto
     * Date Accessed: 25/04/2020
     * Author: Jaret Wright
     * @param url
     * @param rb 
     */
    public void setUser(User user)
    {
        this.user = user;
        UsernameText.setText(this.user.getUsername());
        if(user.getBackgroundColour().equals("Light"))
        {
            leftPane.setStyle("-fx-background-color: Grey");
            rightPane.setStyle("-fx-background-color: Grey");
            //If user selects light, change background
            
        }
        else if(user.getBackgroundColour().equals("Dark"))
        {
            leftPane.setStyle("-fx-background-color: #00283D");
            rightPane.setStyle("-fx-background-color: #00283D");
            //If user selects dark, change background
        }
        UsernameText.setFill(user.getUsernameColour());
                    //add existing script to table
        ImageView imageView = new ImageView(new Image(
                 this.getClass().getResourceAsStream("/Images/deleteBtn.png")));
            //Set delete button
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                                            "dd/MM/yyyy");
                    //Set columns for each field
            ScriptName.setCellValueFactory(new PropertyValueFactory<Script, 
                                                               String>("name"));
            ScriptDate.setCellValueFactory(new PropertyValueFactory<Script,
                                                            LocalDate>("date"));
            ScriptDescription.setCellValueFactory(new PropertyValueFactory
                                               <Script, String>("description"));
            ScriptCommand.setCellValueFactory(new PropertyValueFactory
                                                   <Script, String>("command"));
        if(this.user.getListOfScripts().isEmpty())
        {
            user.getListOfScripts().add(new Script("HCIAssignment.java",
                    formatter.format(LocalDate.of(2020, Month.APRIL, 1)),
                    "This is my first HCI Assignment", 
                    "javac HCIAssignment.java"));
            user.setListOfScripts(user.getListOfScripts());
        }
        scriptTable.setItems(this.user.getListOfScripts());
    }

    
}
