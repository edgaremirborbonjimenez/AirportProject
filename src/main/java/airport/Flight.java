package airport;

import people.Passenger;
import utils.customLinkedList.CustomLinkedList;
import utils.customLinkedList.Node;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Flight {
    private City leaving;
    private City goingTo;
    private double price;
    private Date dateLeaving;
    private Date dateArrival;
    private Airplane airplane;
    private CustomLinkedList<Seat> seats;

    public Flight() {

    }

    public Flight(City leaving, City goingTo, double price, Date dateLeaving, Date dateArrival,Airplane airplane, CustomLinkedList<Seat> seats) {
        this.leaving = leaving;
        this.goingTo = goingTo;
        this.price = price;
        this.dateLeaving = dateLeaving;
        this.dateArrival = dateArrival;
        this.airplane = airplane;
        this.seats = seats;
    }

    public City getLeaving() {
        return leaving;
    }

    public void setLeaving(City leaving) {
        this.leaving = leaving;
    }

    public City getGoingTo() {
        return goingTo;
    }

    public void setGoingTo(City goingTo) {
        this.goingTo = goingTo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateLeaving() {
        return dateLeaving;
    }

    public void setDateLeaving(Date dateLeaving) {
        this.dateLeaving = dateLeaving;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public CustomLinkedList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(CustomLinkedList<Seat> seats) {
        this.seats = seats;
    }

        public Seat asignSeat(Passenger passenger, int number) {
        if (seats.getHead() == null) {
            return null;
        }
        Node<Seat> seat = seats.getHead();
        do {
            if (seat.getData().getNumber() == number) {
                seat.getData().setPassenger(passenger);
                return seat.getData();
            } else {
                seat = seat.getNext();
            }
        } while (seat.getNext() != null);
        return null;
    }

    public void showSeats() {
        this.seats.display();
    }

    @Override
    public String toString() {
        return "Flight{" +
                "leaving=" + leaving +
                ", goingTo=" + goingTo +
                ", price=" + price +
                ", dateLeaving=" + dateLeaving +
                ", dateArrival=" + dateArrival +
                ", airplane=" + airplane +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Double.compare(price, flight.price) == 0 && Objects.equals(leaving, flight.leaving) && Objects.equals(goingTo, flight.goingTo) && Objects.equals(dateLeaving, flight.dateLeaving) && Objects.equals(dateArrival, flight.dateArrival) && Objects.equals(airplane, flight.airplane);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leaving, goingTo, price, dateLeaving, dateArrival, airplane);
    }
}
