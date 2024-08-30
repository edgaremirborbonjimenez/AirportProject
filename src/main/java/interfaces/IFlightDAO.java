package interfaces;

import airport.City;
import airport.Flight;

import java.util.List;

public interface IFlightDAO {
    public List<List<Flight>> getRoute(City leaving, City goingTo)throws Exception;
    public double getRoutePrice(List<Flight> flights)throws Exception;

}
