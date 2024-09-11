package implementation;

import interfaces.IPassengerDAO;
import people.Passenger;
import utils.Model;

public class PassengerDAO implements IPassengerDAO {
    private static Model model;
    private static IPassengerDAO passengerDAO;

    private PassengerDAO() {
        model = Model.getInstance();
    }

    public static IPassengerDAO getInstance() {
        if (passengerDAO == null) {
            passengerDAO = new PassengerDAO();
        }
        return passengerDAO;
    }

    @Override
    public Passenger getPassegerInfo() {
        return model.getPassenger();
    }
}

