package interfaces;

import airport.Airplane;
import airport.Flight;
import exception.AirplaneException;
import people.Pilot;
import people.Stewardess;

import java.util.Collection;

public interface IAriplaneDAO {
    public Airplane createAirplane(String id,Collection<Pilot> pilots, Collection<Stewardess> stewardesses)throws AirplaneException;
    public Airplane getAirplaneById(String id) throws AirplaneException;
    public Airplane asignFlightToAirplane(String id, Flight flight)throws AirplaneException;
    public Collection<Airplane> getAllAirplanes();
}
