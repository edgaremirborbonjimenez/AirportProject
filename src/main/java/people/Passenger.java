package people;

import airport.Ticket;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Passenger extends Person {

    private String passportCode;
    private Collection<Ticket> ticket;

    public Passenger(){}

    public String getPassportCode() {
        return passportCode;
    }

    public void setPassportCode(String passportCode) {
        this.passportCode = passportCode;
    }
    public Collection<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(Collection<Ticket> ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passportCode='" + passportCode + '\'' +
                ", ticket=" + ticket +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(passportCode, passenger.passportCode) && Objects.equals(ticket, passenger.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportCode, ticket);
    }
}


