package implementation;

import airport.Flight;
import airport.Seat;
import airport.Ticket;
import exception.FlightException;
import exception.TicketException;
import interfaces.IFlightDAO;
import interfaces.ITicketDAO;
import utils.Model;

import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements ITicketDAO {

    private static Model model;
    private static ITicketDAO ticketDAO;
    private static IFlightDAO flightDAO;

    private TicketDAO() {
        model = Model.getInstance();
    }

    public static ITicketDAO getInstance() {
        if (ticketDAO == null) {
            ticketDAO = new TicketDAO();
            flightDAO = FlightDAO.getInstance();
        }
        return ticketDAO;
    }

    @Override
    public Ticket buyFlightTicket(Flight flight, Seat seat) throws TicketException {
        if (flight.getPrice() < model.getPassenger().getMoney()) {
            Ticket newTicket = new Ticket();
            newTicket.setFlight(flight);
            newTicket.setSeat(seat);
            Ticket ticketSold = model.getPassenger().addTicket(newTicket);
            if (ticketSold != null) {
                model.getPassenger().buyTicket(flight.getPrice());
                return ticketSold;
            }
        }
        throw new TicketException("Passenger doesn`t have enough money");
    }

    @Override
    public List<Ticket> buyRouteTickets(List<Flight> flights, List<Seat> seats) throws TicketException {
        List<Ticket> tickets = new ArrayList<>();
        try {
            if (flightDAO.getRoutePrice(flights) > model.getPassenger().getMoney()) {
                throw new TicketException("Passenger doesn`t have enough money");
            }
            for (int i = 0; i < flights.size(); i++) {
                Ticket ticket = buyFlightTicket(flights.get(i),seats.get(i));
                tickets.add(ticket);
            }
        } catch (FlightException e) {
            throw new TicketException(e.getMessage());
        }
        return tickets;
    }
}

