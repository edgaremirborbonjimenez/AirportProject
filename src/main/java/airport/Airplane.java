package airport;

import people.Passenger;
import people.Pilot;
import people.Stewardess;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Airplane {
    private String id;
    private Flight flight;
    private List<Seat> seats;
    private List<Stewardess> steward;
    private List<Pilot> pilots;

    public Airplane() {
        this.seats = new LinkedList<>();
        this.steward = new LinkedList<>();
        this.pilots = new LinkedList<>();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Stewardess> getStewardess() {
        return steward;
    }

    public void setStewardess(List<Stewardess> steward) {
        this.steward = steward;
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }

    public Seat asignSeat(Passenger passenger, int number){
        for(Seat seat : seats){
            if(seat.getNumber()==number){
                seat.setPassenger(passenger);
                return seat;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id='" + id + '\'' +
                ", flight=" + flight +
                ", seats=" + seats +
                ", steward=" + steward +
                ", pilots=" + pilots +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return Objects.equals(id, airplane.id) && Objects.equals(flight, airplane.flight) && Objects.equals(seats, airplane.seats) && Objects.equals(steward, airplane.steward) && Objects.equals(pilots, airplane.pilots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight, seats, steward, pilots);
    }
}
