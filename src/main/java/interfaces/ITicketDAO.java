package interfaces;

import airport.Flight;
import airport.Seat;
import airport.Ticket;
import people.Passenger;

import java.util.List;

public interface ITicketDAO {
    Ticket buyFlightTicket(Flight flight, Seat seat, Passenger passenger);

}
