package implementation;

import airport.City;
import exception.AirportException;
import interfaces.ICityDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Model;

import java.util.List;

public class CityDAO implements ICityDAO {

    private static final Logger logger = LogManager.getLogger("Airport");

    private static ICityDAO cityDAO;
    private static final int numRec = 0;
    private static final int count = 4;
    private static Model model;


    private CityDAO() {
        model = Model.getInstance();
    }

    public static ICityDAO getInstance() {
        if (cityDAO == null) {
            cityDAO = new CityDAO();
        }
        return cityDAO;
    }

    @Override
    public List<City> getCities() throws Exception {
        List<City> cities = model.getCities();

        if (cities == null || cities.size() == 0) {
            logger.error("There is No City Available");
            throw new AirportException("There is No City Available");
        }
        return cities;    }

    @Override
    public void setCities(List<City> cities) {
        model.setCities(cities);
    }
}
