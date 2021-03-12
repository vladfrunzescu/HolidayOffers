package socialnetwork.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class FlightDTO {
    private Long ID;
    private String from;
    private String to;
    private LocalDateTime departureTime;
    private LocalDateTime  landingTime;
    private Integer seats;
    private Integer availableSeats;

    public FlightDTO(String from, String to, LocalDateTime departureTime, LocalDateTime landingTime, Integer seats, Integer availableSeats) {
        this.ID = generateRandomId();
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.seats = seats;
        this.availableSeats = availableSeats;
    }

    public FlightDTO(Long Id, String from, String to, LocalDateTime departureTime, LocalDateTime landingTime, Integer seats, Integer availableSeats) {
        this.ID = Id;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.seats = seats;
        this.availableSeats = availableSeats;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(LocalDateTime landingTime) {
        this.landingTime = landingTime;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public Long getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDTO flight = (FlightDTO) o;
        return Objects.equals(from, flight.from) &&
                Objects.equals(to, flight.to) &&
                Objects.equals(departureTime, flight.departureTime) &&
                Objects.equals(landingTime, flight.landingTime) &&
                Objects.equals(seats, flight.seats) &&
                Objects.equals(flight.ID, this.ID) &&
                Objects.equals(flight.availableSeats, this.availableSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, from, to, departureTime, landingTime, seats, availableSeats);
    }

    private Long generateRandomId() {
        long leftLimit = 1L;
        long rightLimit = 1000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
