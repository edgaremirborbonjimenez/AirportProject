package utils;

import airport.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import people.Passenger;
import utils.customLinkedList.CustomLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Model {
    Passenger passenger;
    Airport airport;
    List<City> cities;
    static Model model;
    private static final Logger logger = LogManager.getLogger("Airport");


    private Model() {
        startData();
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public List<Flight> getFlights() {
        return airport.getAllFlights();
    }

    public Flight findFlight(Flight flight) {
        for (Flight f:airport.getAllFlights()) {
            if (f.equals(flight)) {
                return f;
            }
        }
        return null;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    private void startData() {
        cities = new ArrayList<>();
        airport = new Airport();
        passenger = new Passenger();

        City city = new City(ECity.MEXICO);
        City city2 = new City(ECity.USA);
        City city3 = new City(ECity.BRASIL);
        City city4 = new City(ECity.GUATEMALA);
        City city5 = new City(ECity.SPAIN);
        City city6 = new City(ECity.ROME);
        cities.add(city);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        cities.add(city5);
        cities.add(city6);

        Flight flight1 = new Flight();
        flight1.setLeaving(city);
        flight1.setGoingTo(city2);
        flight1.setPrice(50.99);
        Flight flight2 = new Flight();
        flight2.setLeaving(city2);
        flight2.setGoingTo(city3);
        flight2.setPrice(30.99);
        Flight flight3 = new Flight();
        flight3.setLeaving(city3);
        flight3.setGoingTo(city4);
        flight3.setPrice(20.99);
        Flight flight4 = new Flight();
        flight4.setLeaving(city4);
        flight4.setGoingTo(city5);
        flight4.setPrice(40.99);
        Flight flight5 = new Flight();
        flight5.setLeaving(city);
        flight5.setGoingTo(city3);
        flight5.setPrice(19.99);
        Flight flight6 = new Flight();
        flight6.setLeaving(city);
        flight6.setGoingTo(city6);
        flight6.setPrice(40.99);


        List<Flight> flights = new ArrayList<>(Arrays.asList(flight1, flight2, flight3, flight4, flight5,flight6));

        for(Flight f:flights) {
            CustomLinkedList<Seat> seats = new CustomLinkedList<>();
            for (int i = 0; i < 10;i++) {
                Seat s = new Seat();
                s.setNumber(i+1);
                s.setSpecial(false);
                s.setTypeSeat(TypeSeat.GENERAL);
                seats.insert(s);
        }
            f.setSeats(seats);
        }

        Airplane airplane1 = new Airplane();
        flight1.setAirplane(airplane1);
        airplane1.setFlight(flight1);
        //airplane1.setSeats(seats);
        Airplane airplane2 = new Airplane();
        flight2.setAirplane(airplane2);
        airplane2.setFlight(flight2);
        //airplane2.setSeats(seats);
        Airplane airplane3 = new Airplane();
        flight3.setAirplane(airplane3);
        airplane3.setFlight(flight3);
        //airplane3.setSeats(seats);
        Airplane airplane5 = new Airplane();
        flight4.setAirplane(airplane5);
        airplane5.setFlight(flight4);
       // airplane5.setSeats(seats);
        Airplane airplane6 = new Airplane();
        flight5.setAirplane(airplane6);
        airplane6.setFlight(flight5);
        //airplane6.setSeats(seats);
        Airplane airplane7 = new Airplane();
        flight6.setAirplane(airplane7);
        airplane7.setFlight(flight6);
        //airplane7.setSeats(seats);
        airplane1.setId("1");
        airplane2.setId("2");
        airplane3.setId("3");
        airplane5.setId("4");
        airplane6.setId("5");
        airplane7.setId("6");
        airport.addAirplane(airplane7);
        airport.addAirplane(airplane1);
        airport.addAirplane(airplane2);
        airport.addAirplane(airplane3);
        airport.addAirplane(airplane5);
        airport.addAirplane(airplane6);
        passenger = new Passenger();
        passenger.setFirstName("Edgar Emir");
        passenger.setLastName("Borbon Jimenez");
        passenger.setBirthDate(new Date());
        passenger.setMoney(2000);
        logger.info("Data started");
    }
}
