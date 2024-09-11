package interfaces;

import airport.City;
import airport.Flight;
import exception.FlightException;

import java.util.List;

public interface IFlightDAO {
    public List<List<Flight>> getRoute(City leaving, City goingTo)throws FlightException;
    public double getRoutePrice(List<Flight> flights)throws FlightException;

}
