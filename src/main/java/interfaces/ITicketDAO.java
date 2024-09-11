package interfaces;

import airport.Flight;
import airport.Seat;
import airport.Ticket;

import java.util.List;

public interface ITicketDAO {
    Ticket buyFlightTicket(Flight flight, Seat seat);
    List<Ticket> buyRouteTickets(List<Flight> flights, List<Seat> seats);

}
