package implementation;

import airport.Airplane;
import airport.Flight;
import exception.AirplaneException;
import interfaces.IAriplaneDAO;
import people.Pilot;
import people.Stewardess;
import utils.Model;

import java.util.Collection;
import java.util.List;

public class AirplaneDAO implements IAriplaneDAO {

    private static IAriplaneDAO airplaneDAO;
    private static Model model;

    private AirplaneDAO() {
        model = Model.getInstance();
    }

    public static IAriplaneDAO getInstance() {
        if (airplaneDAO == null) {
            airplaneDAO = new AirplaneDAO();
        }
        return airplaneDAO;
    }

    //Streaming
    @Override
    public Airplane createAirplane(String id, Collection<Pilot> pilots, Collection<Stewardess> stewardesses)throws AirplaneException {
        if(getAirplaneById(id) != null) {
            throw new AirplaneException("Airplane with id " + id + " already exists");
        }
        Airplane newAirplane = new Airplane();
        newAirplane.setId(id);
        newAirplane.setStewardess(stewardesses.stream().toList());
        newAirplane.setPilots(pilots.stream().toList());
        model.getAirport().addAirplane(newAirplane);
        return newAirplane;
    }
//Streaming
    @Override
    public Airplane getAirplaneById(String id) throws AirplaneException {
         List<Airplane> list = model.getAirport().getAirplanes().stream().filter(a -> a.getId().equals(id)).toList();
         if (list.isEmpty()) {
             return null;
         }
         return list.get(0);
    }

    @Override
    public Airplane asignFlightToAirplane(String id, Flight flight) throws AirplaneException {
        Airplane airplaneFound = getAirplaneById(id);
        if(airplaneFound == null) {
            throw new AirplaneException("Airplane with id " + id + " does not exist");
        }
        return model.getAirport().asignFlightToAirplane(airplaneFound.getId(), flight);
    }

    @Override
    public Collection<Airplane> getAllAirplanes() {
        return model.getAirport().getAirplanes();
    }
}
