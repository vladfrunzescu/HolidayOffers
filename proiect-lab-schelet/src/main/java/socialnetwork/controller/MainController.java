package socialnetwork.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;
import socialnetwork.domain.*;
import socialnetwork.domain.Client;
import socialnetwork.service.Service;
import socialnetwork.utils.observer.Observer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MainController implements Observer {

    private Client client;
    private Service service;

    private ObservableList<FlightDTO> flightModel = FXCollections.observableArrayList();

    @FXML
    private TableView<FlightDTO> tableView;
    @FXML
    private TableColumn<FlightDTO,String> tableColumnID;
    @FXML
    private TableColumn<FlightDTO,String> tableColumnFrom;
    @FXML
    private TableColumn<FlightDTO,String> tableColumnTo;
    @FXML
    private TableColumn<FlightDTO,String> tableColumnDeparture;
    @FXML
    private TableColumn<FlightDTO,String> tableColumnLanding;
    @FXML
    private TableColumn<FlightDTO,String> tableColumnSeats;
    @FXML
    private TableColumn<FlightDTO,String> tableColumnAvailable;
    @FXML
    private ComboBox<String> comboBoxTo;
    @FXML
    private ComboBox<String> comboBoxFrom;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label labelNume;
    @FXML
    private Pagination pagination;

    @Override
    public void update() {
        this.initModel();
    }

    public void setPage(Client entity, Service service) {
        this.client = entity;
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    @FXML
    public void initialize() {
        tableColumnID.setCellValueFactory(new PropertyValueFactory<FlightDTO, String>("ID"));
        tableColumnFrom.setCellValueFactory(new PropertyValueFactory<FlightDTO, String>("From"));
        tableColumnTo.setCellValueFactory(new PropertyValueFactory<FlightDTO, String>("To"));
        tableColumnDeparture.setCellValueFactory(new PropertyValueFactory<FlightDTO, String>("DepartureTime"));
        tableColumnLanding.setCellValueFactory(new PropertyValueFactory<FlightDTO, String>("LandingTime"));
        tableColumnSeats.setCellValueFactory(new PropertyValueFactory<FlightDTO, String>("Seats"));
        tableColumnAvailable.setCellValueFactory(new PropertyValueFactory<FlightDTO, String>("AvailableSeats"));

        tableView.setItems(flightModel);
    }


    private void updateNotificationsTableView(int page) {
        flightModel.setAll(service.getPaginatedFlights(page));
    }

    private void initModel() {

        int total_pages = (int) (Math.ceil(service.getAllFlightsDTO().size() * 1.0 / 5));
        if (total_pages == 0) total_pages = 1;
        pagination.setPageCount(total_pages);
        pagination.setCurrentPageIndex(0);

        pagination.currentPageIndexProperty().addListener(
                ((observable, oldValue, newValue) -> updateNotificationsTableView(newValue.intValue()))
        );

        flightModel.setAll(service.getPaginatedFlights(0));
        labelNume.setText(client.getName());

        List<String> from_locations = service.getFromLocations();
        List<String> to_locations = service.getToLocations();

        ObservableList<String> observableListFrom = FXCollections.observableArrayList(from_locations);
        comboBoxFrom.setItems(observableListFrom);
        comboBoxFrom.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item);
                        }
                    }
                };
            }
        });
        comboBoxFrom.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String o) {
                if (o == null) {
                    return null;
                } else {
                    return o;
                }
            }

            @Override
            public String fromString(String string) {
                return null;
            }
        });


        ObservableList<String> observableListTo = FXCollections.observableArrayList(to_locations);
        comboBoxTo.setItems(observableListTo);
        comboBoxTo.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item);
                        }
                    }
                };
            }
        });
        comboBoxTo.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String o) {
                if (o == null) {
                    return null;
                } else {
                    return o;
                }
            }

            @Override
            public String fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    public void handleCautaButton(){
        String from = comboBoxFrom.getSelectionModel().getSelectedItem();
        String to = comboBoxTo.getSelectionModel().getSelectedItem();
        LocalDate data =datePicker.getValue();

        if(from != null && to != null && data != null) {
            List<Flight> flights = service.getFlightsByParams(from, to, data.atTime(00,00,00));

            List<FlightDTO> list = new ArrayList<>();
            for(Flight e : flights) {
                Integer ticketsSold = service.getSoldTickets(e);
                FlightDTO dto = new FlightDTO(e.getId(), e.getFrom(), e.getTo(), e.getDepartureTime(), e.getLandingTime(), e.getSeats(), e.getSeats() - ticketsSold);
                list.add(dto);
            }
            flightModel.setAll(list);
        }

    }

    @FXML
    public void handleCumparaButton(){
        FlightDTO flight = tableView.getSelectionModel().getSelectedItem();
        if(flight != null){
            Ticket ticket = new Ticket(client.getId(), flight.getID(), LocalDateTime.now());
            service.addTicket(ticket);
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Ticket", "Ati cumparat un bilet!");
        }else{
            MessageAlert.showErrorMessage(null, "Nu s-a selectat niciun zbor!");
        }
    }

    /*

    @FXML
    public void handleTrimiteButton() {
        try {
            String text_message = textField.getText();
            List<Long> all_entities = new ArrayList<>();
            for(Client e : service.getAllClient()){
                if(!e.equals(client)){
                    all_entities.add(e.getId());
                }
            }

            //service.addEntity();
            //MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Mesaj", "Mesajul a fost trimis cu succes!");
            textField.setText("");
        }
        catch (Exception ex) {
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    public void handleRetragButton(){
        try{
            //if(service.action() == null){
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "", "!");
            //}
        } catch(Exception ex){
                MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    public void handleRevinButton(){
        try{
            //if(service.action() == null){
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "", "!");
            //}
        } catch(Exception ex){
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

 */
}
