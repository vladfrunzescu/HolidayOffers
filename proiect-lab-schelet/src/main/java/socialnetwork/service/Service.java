package socialnetwork.service;

import socialnetwork.domain.*;
import socialnetwork.repository.Repository;
import socialnetwork.utils.observer.Observable;
import socialnetwork.utils.observer.Observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Service implements Observable {
    private Repository<String, Client> repoClient;
    private Repository<Long, Flight> repoFlight;
    private Repository<Long,Ticket> repoTicket;

    @Override
    public void addObserver(Observer e){
        observers.add(e);
    }
    @Override
    public void removeObserver(Observer e){
        observers.remove(e);
    }
    @Override
    public void notifyObservers(){
        observers.stream().forEach(x->x.update());
    }

    public Service(Repository<String, Client> repoClient, Repository<Long, Flight> repoFlight, Repository<Long, Ticket> repoTicket) {
        this.repoClient = repoClient;
        this.repoFlight = repoFlight;
        this.repoTicket = repoTicket;
    }

    public Client getOneClient (String ID){
        return repoClient.findOne(ID);
    }

    public List<Flight> getAllFlights(){
        List<Flight> list = new ArrayList<>();
        repoFlight.findAll().forEach(list::add);
        return list;
    }
    
    public List<String> getFromLocations(){
        List<String> from = new ArrayList<>();
        for(Flight f : getAllFlights()){
            if(!from.contains(f.getFrom())){
                from.add(f.getFrom());
            }
        }
        return from;
    }

    public List<String> getToLocations(){
        List<String> to = new ArrayList<>();
        for(Flight f : getAllFlights()){
            if(!to.contains(f.getTo())){
                to.add(f.getTo());
            }
        }
        return to;
    }

    public List<Flight> getFlightsByParams(String from, String to, LocalDateTime data) {
        List<Flight> filtered_Flights = new ArrayList<>();

        for(Flight f : getAllFlights()){
            if(f.getFrom().equals(from) && f.getTo().equals(to) && f.getDepartureTime().getYear() == data.getYear() && f.getDepartureTime().getMonth() == data.getMonth() && f.getDepartureTime().getDayOfMonth() == data.getDayOfMonth()){
                filtered_Flights.add(f);
            }
        }
        return filtered_Flights;
    }

    public Ticket addTicket(Ticket ticket){
        Ticket result = repoTicket.save(ticket);
        if(result == null){
            this.notifyObservers();
        }
        return result;
    }

    public List<FlightDTO> getAllFlightsDTO(){
        List<FlightDTO> list = new ArrayList<>();
        for(Flight e : this.getAllFlights()) {
            Integer ticketsSold = getSoldTickets(e);
            FlightDTO dto = new FlightDTO(e.getId(), e.getFrom(), e.getTo(), e.getDepartureTime(), e.getLandingTime(), e.getSeats(), e.getSeats() - ticketsSold);
            list.add(dto);
        }
        return list;
    }

    public Integer getSoldTickets(Flight f) {
        List<Ticket> tickets = getAllTickets();
        Integer sold = 0;
        for(Ticket t : tickets){
            if(t.getFlightID().equals(f.getId())){
                sold += 1;
            }
        }
        return sold;
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> list = new ArrayList<>();
        repoTicket.findAll().forEach(list::add);
        return list;
    }

    public List<FlightDTO> getPaginatedFlights(Integer page){
        List<FlightDTO> list = new ArrayList<>();
        List<FlightDTO> all = getAllFlightsDTO();
        System.out.println(page*5 + "\n");
        for(int i = page*5; i<page*5+5; i++){
            if(i < all.size()) {
                list.add(all.get(i));
            }
        }
        return list;
    }
}
