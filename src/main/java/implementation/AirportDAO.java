package implementation;

import airport.Airplane;
import airport.Airport;
import airport.City;
import airport.Flight;
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
//                airport = new Airport();
//                cities = new ArrayList<City>();
//                City city = new City();
//                city.setName("Mexico");
//                City city2 = new City();
//                city2.setName("USA");
//                City city3 = new City();
//                city3.setName("Brasil");
//                City city4 = new City();
//                city4.setName("Guatemala");
//                City city5 = new City();
//                city5.setName("España");
//                City city6 = new City();
//                city6.setName("Roma");
//                cities.add(city);
//                cities.add(city2);
//                cities.add(city3);
//                cities.add(city4);
//                cities.add(city5);
//                cities.add(city6);
//                Flight flight1 = new Flight();
//                flight1.setLeaving(city);
//                flight1.setGoingTo(city2);
//                flight1.setPrice(50.99);
//                Flight flight2 = new Flight();
//                flight2.setLeaving(city2);
//                flight2.setGoingTo(city3);
//                flight2.setPrice(30.99);
//                Flight flight3 = new Flight();
//                flight3.setLeaving(city3);
//                flight3.setGoingTo(city4);
//                flight3.setPrice(20.99);
//                Flight flight4 = new Flight();
//                flight4.setLeaving(city4);
//                flight4.setGoingTo(city5);
//                flight4.setPrice(40.99);
//                Flight flight5 = new Flight();
//                flight5.setLeaving(city);
//                flight5.setGoingTo(city3);
//                flight5.setPrice(20.99);
//                Flight flight6 = new Flight();
//                flight6.setLeaving(city);
//                flight6.setGoingTo(city6);
//                flight6.setPrice(40.99);
//                Airplane airplane1 = new Airplane();
//                airplane1.setFlight(flight1);
//                Airplane airplane2 = new Airplane();
//                airplane2.setFlight(flight2);
//                Airplane airplane3 = new Airplane();
//                airplane3.setFlight(flight3);
//                Airplane airplane4 = new Airplane();
//                airplane4.setFlight(flight3);
//                Airplane airplane5 = new Airplane();
//                airplane5.setFlight(flight4);
//                Airplane airplane6 = new Airplane();
//                airplane6.setFlight(flight5);
//                Airplane airplane7 = new Airplane();
//                airplane7.setFlight(flight6);
//                this.addAirplane(airplane1);
//                this.addAirplane(airplane2);
//                this.addAirplane(airplane3);
//                this.addAirplane(airplane4);
//                this.addAirplane(airplane5);
//                this.addAirplane(airplane6);
//                this.addAirplane(airplane7);
        }

        public static AirportDAO getAirportDAOInstance(){
            if(airportDAO==null){
                airportDAO = new AirportDAO();
            }
            return airportDAO;
        }

        @Override
        public List<List<Flight>> getRoute(City leaving,City goingTo)throws Exception {
            if(airport == null){
                logger.error("There is No Airport Available");
                throw new Exception("There is No Airport Available");
            }
            return airport.getRoute(leaving,goingTo,count,numRec);
        }

        @Override
        public Airplane addAirplane(Airplane airplane) {
            return airport.addAirplane(airplane);
        }

    @Override
    public double getRoutePrice(List<Flight> flights)throws Exception {
            if (flights == null || flights.size() == 0) {
                logger.error("There is No Flight Available");
                throw new Exception("There is No Flight Available");
            }
        return airport.getRoutePrice(flights);
    }

    @Override
    public List<City> getCities()throws Exception {
            if(cities==null||cities.size()==0){
                logger.error("There is No City Available");
                throw new Exception("There is No City Available");
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
