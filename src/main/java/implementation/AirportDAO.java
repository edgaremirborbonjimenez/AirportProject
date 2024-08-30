package implementation;

import airport.Airplane;
import airport.Airport;
import airport.City;
import airport.Flight;
import exception.AirportException;
import interfaces.IAirportDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AirportDAO implements IAirportDAO {
private static final Logger logger = LogManager.getLogger("Airport");

    private static AirportDAO airportDAO;
    private static Airport airport;
    private static final int numRec = 0;
    private static final int count = 4;
    private static List<City> cities;

        private AirportDAO(){
        }

        public static AirportDAO getAirportDAOInstance(){
            if(airportDAO==null){
                airportDAO = new AirportDAO();
            }
            return airportDAO;
        }

        @Override
        public List<List<Flight>> getRoute(City leaving,City goingTo)throws AirportException {
            if(airport == null){
                logger.error("There is No Airport Available");
                throw new AirportException("There is No Airport Available");
            }
            return airport.getRoute(leaving,goingTo,count,numRec);
        }

        @Override
        public Airplane addAirplane(Airplane airplane) {
            return airport.addAirplane(airplane);
        }

    @Override
    public double getRoutePrice(List<Flight> flights)throws AirportException {
            if (flights == null || flights.size() == 0) {
                logger.error("There is No Flight Available");
                throw new AirportException("There is No Flight Available");
            }
        return airport.getRoutePrice(flights);
    }

    @Override
    public List<City> getCities()throws AirportException {
            if(cities==null||cities.size()==0){
                logger.error("There is No City Available");
                throw new AirportException("There is No City Available");
            }
        return cities;
    }

    @Override
    public void setAirport(Airport airport){
            this.airport = airport;
    }

    @Override
    public void setCities(List<City> cities){
            this.cities = cities;
    }
}
