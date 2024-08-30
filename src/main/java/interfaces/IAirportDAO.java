package interfaces;

import airport.Airplane;
import airport.Airport;
import airport.City;
import airport.Flight;
import exception.AirportException;

import java.util.List;

public interface IAirportDAO {

    public List<List<Flight>> getRoute(City leaving, City goingTo)throws AirportException;
    public Airplane addAirplane(Airplane airplane);
    public double getRoutePrice(List<Flight> flights)throws AirportException;
    public List<City> getCities()throws AirportException;
    public void setAirport(Airport airports);
    public void setCities(List<City> cities);

}
