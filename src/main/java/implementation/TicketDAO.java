package implementation;

import airport.Flight;
import airport.Seat;
import airport.Ticket;
import exception.FlightException;
import exception.TicketException;
import interfaces.IFlightDAO;
import interfaces.ITicketDAO;
import people.Passenger;
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
    public Ticket buyFlightTicket(Flight flight, Seat seat, Passenger passenger) throws TicketException {
        if (flight.getPrice() < passenger.getMoney()) {
            Ticket newTicket = new Ticket();
            newTicket.setFlight(flight);
            newTicket.setSeat(seat);
            Ticket ticketSold = passenger.addTicket(newTicket);
            if (ticketSold != null) {
                passenger.buyTicket(flight.getPrice());
                return ticketSold;
            }
        }
        throw new TicketException("Passenger doesn`t have enough money");
    }
}

