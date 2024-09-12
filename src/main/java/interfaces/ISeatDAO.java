package interfaces;

import airport.Flight;
import airport.Seat;
import people.Passenger;

import java.util.List;

public interface ISeatDAO {
    Seat asignSeat(Flight flight, Passenger p,Seat seat);
    List<Seat> getSeatsByFlight(Flight flight);
}
