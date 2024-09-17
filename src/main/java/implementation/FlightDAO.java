package implementation;

import airport.*;
import exception.FlightException;
import interfaces.IFlightDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Model;
import utils.TypeSeat;
import utils.Utils;
import utils.customLinkedList.CustomLinkedList;

import java.util.List;
import java.util.stream.Collectors;

public class FlightDAO implements IFlightDAO {
    private static final Logger logger = LogManager.getLogger("Airport");

    private static IFlightDAO flightDAO;
    private static final int numRec = 0;
    private static final int count = 4;
    private static Model model;

    private FlightDAO() {
        model = Model.getInstance();
    }

    public static IFlightDAO getInstance() {
        if (flightDAO == null) {
            flightDAO = new FlightDAO();
        }
        return flightDAO;
    }

    @Override
    public List<List<Flight>> getRoute(City leaving, City goingTo) throws FlightException {
        Airport airport = model.getAirport();
        if (airport == null) {
            logger.error("There is No Airport Available");
            throw new FlightException("There is No Airport Available");
        }
        return airport.getRoute(leaving,goingTo,count,numRec);    }

    @Override
    public double getRoutePrice(List<Flight> flights) throws FlightException {
        Airport airport = model.getAirport();

        if (flights == null || flights.size() == 0) {
            logger.error("There is No Flight Available");
            throw new FlightException("There is No Flight Available");
        }
        return airport.getRoutePrice(flights);
    }
//Streaming
    @Override
    public Flight createFlight(City leaving, City goingTo, double price, String dateLeaving, String dateArrival, int amountPremiumSeats, int amountGeneralSeats) throws FlightException {
        List<Flight> flights = model.getFlights().stream().filter(f-> f.getLeaving().equals(leaving) && f.getGoingTo().equals(goingTo)).toList();
        if(!flights.isEmpty()) {
            throw new FlightException("This flight is already exist");
        }
        if(!Utils.isValidDateFormat(dateLeaving) || !Utils.isValidDateFormat(dateArrival)) {
            throw new FlightException("Invalid date format");
        }
        if(amountGeneralSeats == 0 && amountPremiumSeats == 0) {
            throw new FlightException("Flight should have at least one seat");
        }
        Flight flightCreated = new Flight();
        flightCreated.setLeaving(leaving);
        flightCreated.setGoingTo(goingTo);
        flightCreated.setPrice(price);
        flightCreated.setDateLeaving(Utils.parseStringToDate(dateLeaving));
        flightCreated.setDateArrival(Utils.parseStringToDate(dateArrival));
        CustomLinkedList<Seat> seats = new CustomLinkedList<>();
        int numSeat = 1;
        for(int i = 0; i < amountPremiumSeats; i++) {
            Seat seat = new Seat();
            seat.setTypeSeat(TypeSeat.PREMIUM);
            seat.setNumber(numSeat);
            seat.setSpecial(false);
            seats.insert(seat);
            numSeat++;
        }
        for(int i = 0; i < amountGeneralSeats; i++) {
            Seat seat = new Seat();
            seat.setTypeSeat(TypeSeat.GENERAL);
            seat.setNumber(numSeat);
            seat.setSpecial(false);
            seats.insert(seat);
            numSeat++;
        }
        flightCreated.setSeats(seats);
        return flightCreated;
    }
}

