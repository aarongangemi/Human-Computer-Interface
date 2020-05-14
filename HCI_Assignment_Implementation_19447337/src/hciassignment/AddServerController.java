package hciassignment;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/****************************************************
 * Purpose: Add Server Controller
 * Author: Aaron Gangemi
 * Date Modified: 08/05/2020
 */
public class AddServerController {
    @FXML
    private TextField serverURLField;

    @FXML
    private TextField ServerNameField;

    @FXML
    private Label serverNameLabel;

    @FXML
    private Label urlLabel;
    
    private User user;
    @FXML
    private AnchorPane anchorPane;

   /****************************************************
    * Purpose: When user clicks "Add Server", they will be shown the main server
    * page in which the server will be added to the list.
    * @param event
    * @throws IOException 
    *********************************************************/
    @FXML
    void clickAddServer(ActionEvent event) throws IOException, URISyntaxException {
        try
        {
           if(!serverURLField.getText().isEmpty()
           || !ServerNameField.getText().isEmpty())
            {
                new URL(serverURLField.getText()).toURI();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Server.fxml"));
                //Go to server 
                loader.load();
                ServerController serverController = loader.getController();
                serverController.setUser(user);
                //Send user data for server to user
                serverController.addServerToTable(ServerNameField.getText());
                //Add server to list. It does not actually, 
                //however the text is replaced
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                Stage stage = (Stage)
                        ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
           else
           {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Empty fields found");
                alert.setHeaderText("Unable to add server");
                alert.setContentText("Please ensure that no fields are empty"
                        + " and the url is valid. Thanks");
                alert.showAndWait();
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
            serverURLField.setText("Enter Server URL");
            ServerNameField.setText("Enter Server Name");
        }
    }

       /****************************************************
        * Purpose: When user clicks the example label, it will also clear the 
        * server field and the label
        * @param event 
        *****************************************************/
    @FXML
    void clickNameLabel(MouseEvent event) {
        serverNameLabel.setText("");
        ServerNameField.clear();
    }

        /****************************************************
        * Purpose: When user clicks the name field, it will clear the 
        * server field and the label
        * @param event 
        *****************************************************/
    @FXML
    void clickServerNameField(MouseEvent event) {
        serverNameLabel.setText("");
        ServerNameField.clear();
    }

    /****************************************************
     * Purpose: When the user clicks on the server URL field, clear the label 
     * and field
     * @param event 
     ******************************************************/
    @FXML
    void clickServerURLField(MouseEvent event) {
        urlLabel.setText("");
        serverURLField.clear();
    }

    /****************************************************
     * Purpose: When the user clicks the URL example label, also clear the field
     * @param event 
     *****************************************************/
    @FXML
    void clickURLLabel(MouseEvent event) {
        urlLabel.setText("");
        serverURLField.clear();
    }

    /*********************************************************
     * Purpose: When the user clicks the previous page, they will be
     * transitioned back to the server page.
     * @param event
     * @throws IOException 
     */
    @FXML
    void goBack(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Server.fxml"));
        //Go to server page
        loader.load();
        ServerController serverController = loader.getController();
        serverController.setUser(user);
        //Pass any user data to be displayed on server page
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /****************************************************
     * Purpose: All pages will contain a setUser(User user) method which
     * passes around the user data to be displayed on each page.
     * @param user 
     */
    public void setUser(User user)
    {
        this.user = user;
        if(user.getBackgroundColour().equals("Light"))
        {
            anchorPane.setStyle("-fx-background-color:  grey");
            //Change background color of this page if theme is light
        }
        else if(user.getBackgroundColour().equals("Dark"))
        {
            anchorPane.setStyle("-fx-background-color:#00283D");
            //Change background color of this page is theme is dark
        }
    }


}