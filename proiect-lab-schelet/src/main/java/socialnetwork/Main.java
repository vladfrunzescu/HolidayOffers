package socialnetwork;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socialnetwork.controller.LoginController;
import socialnetwork.controller.MainController;
import socialnetwork.domain.*;
import socialnetwork.domain.validators.*;
import socialnetwork.repository.Repository;
import socialnetwork.repository.file.*;
import socialnetwork.service.Service;
import socialnetwork.service.ServiceSchelet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    //private static List<String> argumente = new ArrayList<>();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Repository<String, Client> repoClient = new ClientFile("data/clienti.txt", new ClientValidator());
        Repository<Long, Flight> repoFlight = new FlightFile("data/flights.txt", new FlightValidator());
        Repository<Long, Ticket> repoTicket = new TicketFile("data/tickets.txt", new TicketValidator(repoClient, repoFlight));
        Service service =  new Service(repoClient, repoFlight, repoTicket);

        //deschidem pagina de login
        Stage stage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/loginView.fxml"));
            AnchorPane root = loader.load();
            LoginController controller = loader.getController();
            controller.setPage(service);
            stage.setTitle("Pagina de Login");
            stage.setScene(new Scene(root, 632, 405));
            stage.show();

        } catch(Exception ex){
            ex.printStackTrace();
        }

    }
        /*
        Repository<Long, Unu> unuRepo = new UnuFile("data/unu.txt", new UnuValidator());
        Repository<Long, Doi> doiRepo = new DoiFile(unuRepo, "data/doi.txt", new DoiValidator(unuRepo));
        ServiceSchelet serviceSchelet = new ServiceSchelet(unuRepo, doiRepo);

        for(String s : argumente){
            List<String> attr = Arrays.asList(s.split(":"));
            Unu entity = unuRepo.findOne(Long.parseLong(attr.get(1)));
            Stage stage = new Stage();
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/mainView.fxml"));
                AnchorPane root = loader.load();
                MainController controller = loader.getController();
                serviceSchelet.addObserver(controller);
                controller.setPage(entity, serviceSchelet);
                stage.setTitle(entity.getFirst_string());
                stage.setScene(new Scene(root, 632, 405));
                stage.show();

            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for(String s:args){
            argumente.add(s);
        }
        launch(args);
    }

         */
}



