package socialnetwork.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket extends Entity<Long>{
    private String username;
    private Long flightID;
    private LocalDateTime purchaseTime;

    public Ticket(String username, Long flightID, LocalDateTime purchaseTime) {
        this.setId(generateRandomId());
        this.username = username;
        this.flightID = flightID;
        this.purchaseTime = purchaseTime;
    }

    //la deschiderea aplicatiei se introduc obiectele in memorie din fisierul text
    public Ticket(Long ID,String username, Long flightID, LocalDateTime purchaseTime) {
        this.setId(ID);
        this.username = username;
        this.flightID = flightID;
        this.purchaseTime = purchaseTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getFlightID() {
        return flightID;
    }

    public void setFlightID(Long flightID) {
        this.flightID = flightID;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(username, ticket.username) &&
                Objects.equals(flightID, ticket.flightID) &&
                Objects.equals(purchaseTime, ticket.purchaseTime) &&
                Objects.equals(ticket.getId(), this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), username, flightID, purchaseTime);
    }

    private Long generateRandomId() {
        long leftLimit = 1L;
        long rightLimit = 1000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
