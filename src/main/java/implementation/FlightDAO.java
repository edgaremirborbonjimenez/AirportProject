package implementation;

import airport.Airport;
import airport.City;
import airport.Flight;
import exception.FlightException;
import interfaces.IFlightDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Model;

import java.util.List;

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
}

