package implementation;

import airport.Flight;
import airport.Seat;
import exception.FlightException;
import interfaces.ISeatDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    public Seat asignSeat(Flight flight)throws FlightException {
        Flight flightFound = model.findFlight(flight);

        if (flightFound == null) {
            //logger.error("Flight not found");
            throw new FlightException("Flight not found");
        }
        Node<Seat> node = flight.getAirplane().getSeats().getHead();
        if (node.getData() == null) {
            //logger.error("No seats available");
            throw new FlightException("No seats available");
        }
        do {
            if (node.getData().getPassenger() == null) {
                node.getData().setPassenger(model.getPassenger());
                return node.getData();
            }
            node = node.getNext();
        } while (node.getNext() != null);
        //logger.error("No seats available");
        throw new FlightException("No seats available");
    }

    @Override
    public List<Seat> getSeatsByFlight(Flight flight) {
        return List.of();
    }
}
