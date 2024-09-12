package airport;

import people.Passenger;
import people.Pilot;
import people.Stewardess;
import utils.customLinkedList.CustomLinkedList;
import utils.customLinkedList.Node;

import java.util.*;

public class Airplane {
    private String id;
    private Flight flight;
    //private CustomLinkedList<Seat> seats;
    private Collection<Stewardess> steward;
    private Collection<Pilot> pilots;

    public Airplane() {
        //this.seats = new CustomLinkedList<>();
        this.steward = new LinkedList<>();
        this.pilots = new ArrayList<>();
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

//    public CustomLinkedList<Seat> getSeats() {
//        return seats;
//    }
//
//    public void setSeats(CustomLinkedList<Seat> seats) {
//        this.seats = seats;
//    }

    public Collection<Stewardess> getStewardess() {
        return steward;
    }

    public void setStewardess(List<Stewardess> steward) {
        this.steward = steward;
    }

    public Collection<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }

//    public Seat asignSeat(Passenger passenger, int number) {
//        if (seats.getHead() == null) {
//            return null;
//        }
//        Node<Seat> seat = seats.getHead();
//        do {
//            if (seat.getData().getNumber() == number) {
//                seat.getData().setPassenger(passenger);
//                return seat.getData();
//            } else {
//                seat = seat.getNext();
//            }
//        } while (seat.getNext() != null);
//        return null;
//    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id='" + id + '\'' +
                ", steward=" + steward +
                ", pilots=" + pilots +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return Objects.equals(id, airplane.id) && Objects.equals(flight, airplane.flight) && Objects.equals(steward, airplane.steward) && Objects.equals(pilots, airplane.pilots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

