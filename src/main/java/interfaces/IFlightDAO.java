package interfaces;

import airport.Airplane;
import airport.City;
import airport.Flight;
import airport.Seat;
import exception.FlightException;
import utils.customLinkedList.CustomLinkedList;

import java.util.Date;
import java.util.List;

public interface IFlightDAO {
    public List<List<Flight>> getRoute(City leaving, City goingTo)throws FlightException;
    public double getRoutePrice(List<Flight> flights)throws FlightException;
    public Flight createFlight(City leaving,City goingTo, double price,String dateLeaving,String dateArrival,int amountPremiumSeats,int amountGeneralSeats)throws FlightException;

}
