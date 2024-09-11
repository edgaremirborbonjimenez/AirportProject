package implementation;

import airport.Airplane;
import airport.Airport;
import airport.City;
import airport.Flight;
import exception.AirportException;
import interfaces.IAirportDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Model;

import java.util.List;
//private static final Logger logger = LogManager.getLogger("Airport");

public class AirportDAO implements IAirportDAO {

    private static IAirportDAO airportDAO;
    private static final int numRec = 0;
    private static final int count = 4;
    private static Model model;

    private AirportDAO() {
        model = Model.getInstance();
    }

    public static IAirportDAO getInstance() {
        if (airportDAO == null) {
            airportDAO = new AirportDAO();
        }
        return airportDAO;
    }


    @Override
    public void setAirport(Airport airport) {
        model.setAirport(airport);
    }

}