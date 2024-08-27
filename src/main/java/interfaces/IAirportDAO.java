package interfaces;

import airport.Airplane;
import airport.Airport;
import airport.City;
import airport.Flight;

import java.util.List;

public interface IAirportDAO {

    public List<List<Flight>> getRoute(City leaving, City goingTo)throws Exception;
    public Airplane addAirplane(Airplane airplane);
    public double getRoutePrice(List<Flight> flights)throws Exception;
    public List<City> getCities()throws Exception;
    public void setAirport(Airport airports);
    public void setCities(List<City> cities);

}
