package implementation;

import airport.Flight;
import airport.Seat;
import exception.FlightException;
import interfaces.ISeatDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import people.Passenger;
import utils.Model;
import utils.customLinkedList.Node;

import java.util.List;

public class SeatDAO implements ISeatDAO {

    private static final Logger logger = LogManager.getLogger("Airport");
    private static Model model;
    private static ISeatDAO seatDAO;


    private SeatDAO() {
        model = Model.getInstance();
    }

    public static ISeatDAO getInstance() {
        if (seatDAO == null) {
            seatDAO = new SeatDAO();
        }
        return seatDAO;
    }

    @Override
    public Seat asignSeat(Flight flight, Passenger p,Seat seat)throws FlightException {
        Flight flightFound = model.findFlight(flight);

        if (flightFound == null) {
            logger.error("Flight not found");
            throw new FlightException("Flight not found");
        }

        if(p == null) {
            logger.error("Passenger not found");
            throw new FlightException("Passenger not found");
        }
        Seat seatAssigned = flightFound.asignSeat(p,seat.getNumber());
        if(seatAssigned == null) {
            logger.error("Couldn't assign seat");
            throw new FlightException("Couldn't assign seat");
        }
        return seatAssigned;

    }

    @Override
    public List<Seat> getSeatsByFlight(Flight flight) {
        return List.of();
    }
}
