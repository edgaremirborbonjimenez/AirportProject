package presentation;

import airport.Airplane;
import airport.Airport;
import airport.City;
import airport.Flight;
import implementation.AirportDAO;
import interfaces.IAirportDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static IAirportDAO airportDAO;
    private static final Logger logger = LogManager.getLogger("Airport");


    public Menu() {
        airportDAO = AirportDAO.getAirportDAOInstance();
        this.startData();
    }

    private void startData() {

        Airport airport = new Airport();
        List<City> cities = new ArrayList<City>();
        City city = new City();
        city.setName("Mexico");
        City city2 = new City();
        city2.setName("USA");
        City city3 = new City();
        city3.setName("Brasil");
        City city4 = new City();
        city4.setName("Guatemala");
        City city5 = new City();
        city5.setName("España");
        City city6 = new City();
        city6.setName("Roma");
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
        flight5.setPrice(20.99);
        Flight flight6 = new Flight();
        flight6.setLeaving(city);
        flight6.setGoingTo(city6);
        flight6.setPrice(40.99);
        Airplane airplane1 = new Airplane();
        airplane1.setFlight(flight1);
        Airplane airplane2 = new Airplane();
        airplane2.setFlight(flight2);
        Airplane airplane3 = new Airplane();
        airplane3.setFlight(flight3);
        Airplane airplane4 = new Airplane();
        airplane4.setFlight(flight3);
        Airplane airplane5 = new Airplane();
        airplane5.setFlight(flight4);
        Airplane airplane6 = new Airplane();
        airplane6.setFlight(flight5);
        Airplane airplane7 = new Airplane();
        airplane7.setFlight(flight6);
        airport.addAirplane(airplane1);
        airport.addAirplane(airplane2);
        airport.addAirplane(airplane3);
        airport.addAirplane(airplane4);
        airport.addAirplane(airplane5);
        airport.addAirplane(airplane6);
        airport.addAirplane(airplane7);
        airportDAO.setAirport(airport);
        airportDAO.setCities(cities);
        logger.info("Data started");

    }

    static {
        System.out.println("Welcome to Airport Menu");
    }

    public void displayMenu() {
        System.out.println("What do you want to do?");
        while (true) {
            System.out.println("1. Find a flights to go to somewhere");
            System.out.println("Press 0 to exit");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1) {
                selectDestination();
            }else if(option == 0){
                break;
            }else{
                System.out.println("Please select a valid option");
            }
        }
    }

    public void selectDestination(){
        try {
            System.out.println("Select where you are leaving");
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < airportDAO.getCities().size(); i++) {
                System.out.println((i + 1) + ".-" + airportDAO.getCities().get(i).getName());
            }
            int optionLeaving = scanner.nextInt();
            if (optionLeaving > airportDAO.getCities().size()) {
                System.out.println("Invalid option");
                selectDestination();
            } else {
                System.out.println("Select destination");
                for (int i = 0; i < airportDAO.getCities().size(); i++) {
                    System.out.println((i + 1) + ".-" + airportDAO.getCities().get(i).getName());
                }
                int optionGoingTo = scanner.nextInt();
                if (optionGoingTo > airportDAO.getCities().size()) {
                    System.out.println("Invalid option");
                    selectDestination();
                } else {
                    if (optionLeaving == optionGoingTo) {
                        System.out.println("Please select a destination different of the leaving place");
                        selectDestination();
                    } else {
                        showRoutes(airportDAO.getRoute(airportDAO.getCities().get(optionLeaving - 1), airportDAO.getCities().get(optionGoingTo - 1)));
                    }
                }
            }
        }catch (Exception e){
            System.err.println(e.getMessage()+ " Talk to the IT Department");
            displayMenu();
        }
    }
    public void showRoutes(List<List<Flight>> routes)throws Exception{
        for(int i = 0;i<routes.size();i++){
            System.out.println("Route"+ (i+1));
            int cont = routes.get(i).size();
            System.out.println("Flights needed to take");
            for(Flight flight : routes.get(i)){
                System.out.println("Flight #"+cont+" Leave: "+flight.getLeaving().getName() + ", GoingTo: " + flight.getGoingTo().getName()+" -- Price: "+ flight.getPrice());
                cont--;
            }
            System.out.println("Price for the complete route: "+ airportDAO.getRoutePrice(routes.get(i)));
            System.out.println("---------------------------");
        }
    }
}
