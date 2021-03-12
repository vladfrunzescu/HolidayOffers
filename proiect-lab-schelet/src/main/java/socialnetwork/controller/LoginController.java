package socialnetwork.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socialnetwork.domain.Client;
import socialnetwork.service.Service;
import socialnetwork.service.ServiceSchelet;

public class LoginController {

    private Service service;

    @FXML
    private TextField textFieldLogin;

    @FXML
    public void handleButtonLogin() {
        String userName = textFieldLogin.getText();
        if (service.getOneClient(userName) != null) {
            openFlightsPage(service.getOneClient(userName));}
        else{
                MessageAlert.showErrorMessage(null, "Username inexistent!");
            }
    }

    public void openFlightsPage(Client client){

        Stage stage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/mainView.fxml"));
            AnchorPane root = loader.load();
            MainController controller = loader.getController();
            controller.setPage(client, service);
            stage.setTitle(client.getName());
            stage.setScene(new Scene(root, 632, 505));
            stage.show();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void setPage(Service serviceSchelet) {
        this.service = serviceSchelet;
    }

    @FXML
    public void initialize() {
    }




}
