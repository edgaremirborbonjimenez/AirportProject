package presentation;

import airport.*;
import implementation.*;
import interfaces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private IAirportDAO airportDAO;
    private IFlightDAO flightDAO;
    private ICityDAO cityDAO;
    private ITicketDAO ticketDAO;
    private ISeatDAO seatDAO;
    private IPassengerDAO passengerDAO;
    private Scanner scanner;
    private static final Logger logger = LogManager.getLogger("Airport");

    public Menu() {

        airportDAO = AirportDAO.getInstance();
        flightDAO = FlightDAO.getInstance();
        cityDAO = CityDAO.getInstance();
        ticketDAO = TicketDAO.getInstance();
        seatDAO = SeatDAO.getInstance();
        passengerDAO = PassengerDAO.getInstance();
        scanner = new Scanner(System.in);

    }

    static {
        System.out.println("Welcome to Airport Menu");
    }

    public void displayMenu() {
        do {
            System.out.println("What do you want to do?");
            System.out.println("1.- Find a flights to go to somewhere");
            System.out.println("2.- See my tickets");
            System.out.println("Press 3 to exit");


            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();

                if (option == 1) {
                    selectDestination();
                } else if (option == 2) {
                    seeMyTickets();
                } else if (option == 3) {
                    scanner.close();
                    return;
                } else {
                    System.out.println("Please select a valid option");
                }
            } else {
                System.out.println("Please select a valid option");
                System.out.println();
            }
        } while (true);
    }

    public void selectDestination() {

        int leavingOpt;
        int goingToOpt;
        do {
            try {
                System.out.println("Select where you are leaving");
                for (int i = 0; i < cityDAO.getCities().size(); i++) {
                    System.out.println((i + 1) + ".-" + cityDAO.getCities().get(i).getName());
                }

                if (!scanner.hasNextInt()) {
                    scanner.next();
                    logger.error("Please select a valid option in Main Menu");
                    System.out.println("Please select a valid option for Leaving");
                    System.out.println();
                    continue;
                }

                leavingOpt = scanner.nextInt();

                System.out.println("Select destination");
                for (int i = 0; i < cityDAO.getCities().size(); i++) {
                    System.out.println((i + 1) + ".-" + cityDAO.getCities().get(i).getName());
                }


                if (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Please select a valid option for Destination");
                    System.out.println();
                    continue;
                }

                goingToOpt = scanner.nextInt();

                if (leavingOpt == goingToOpt) {
                    System.out.println("Please select a destination different of the leaving place");
                    System.out.println();
                    continue;
                }

                showRoutes(flightDAO.getRoute(cityDAO.getCities().get(leavingOpt - 1), cityDAO.getCities().get(goingToOpt - 1)));
                break;

            } catch (Exception e) {
                System.err.println(e.getMessage() + " Talk to the IT Department");
            }

        } while (true);
    }


    public void showRoutes(List<List<Flight>> routes)throws Exception {
        Scanner scanner = new Scanner(System.in);

        do {
            for (int i = 0;i < routes.size();i++) {
                System.out.println("Route" + (i + 1));
                int cont = routes.get(i).size();
                System.out.println("Flights needed to take");
                for (Flight flight : routes.get(i)) {
                    System.out.println("Flight #" + cont + " Leave: " + flight.getLeaving().getName() + ", GoingTo: " + flight.getGoingTo().getName() + " -- Price: " + flight.getPrice());
                    cont--;
                }
                System.out.println("Price for the complete route: " + flightDAO.getRoutePrice(routes.get(i)));
                System.out.println("---------------------------");
            }
            System.out.println("----Select a route----");

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please select a valid option");
                System.out.println();
                continue;
            }

            int option = scanner.nextInt();

            if (option > routes.size() || option < 0) {
                System.out.println("Please select a valid option");
                continue;
            }
            chooseRoute(option,routes);
            break;

        } while (true);
    }

    public void chooseRoute(int routeIndex,List<List<Flight>> routes) throws Exception {
        Scanner scanner = new Scanner(System.in);
        do {
            int cont = 1;
            for (Flight flight : routes.get(routeIndex - 1)) {
                System.out.println("Flight #" + (cont) + " Leave: " + flight.getLeaving().getName() + ", GoingTo: " + flight.getGoingTo().getName() + " -- Price: " + flight.getPrice());
                cont++;
            }
            System.out.println("Price for the complete route: " + flightDAO.getRoutePrice(routes.get(routeIndex - 1)));
            System.out.println("---------Options---------");
            System.out.println("1.- Buy All the Route");
            System.out.println("2 .- Buy one flight");
            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please select a valid option");
                System.out.println();
                continue;
            }
            int option = scanner.nextInt();
            if (option == 1) {
                buyAllRouteTickets(routes.get(routeIndex - 1));
                break;
            } else if (option == 2) {
                chooseFlight(routes.get(routeIndex - 1));
                break;
            } else {
                System.out.println("Please select a valid option");
                System.out.println();
                continue;
            }
        } while (true);
    }

    public void chooseFlight(List<Flight> flights) {
        Scanner scanner = new Scanner(System.in);
        do {
            int cont = 1;
            for (Flight flight : flights) {
                System.out.println("Flight #" + (cont) + " Leave: " + flight.getLeaving().getName() + ", GoingTo: " + flight.getGoingTo().getName() + " -- Price: " + flight.getPrice());
                cont++;
            }
            System.out.println("Select a flight");
            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please select a valid option");
                System.out.println();
                continue;
            }
            int option = scanner.nextInt();

            if (option > flights.size() || option < 0) {
                System.out.println("Please select a valid option");
                System.out.println();
                continue;
            }
            buyFlight(option, flights);
            break;
        } while (true);
    }

    public void buyFlight(int flightIndex,List<Flight> route) {
        if (route.get(flightIndex - 1).getPrice() > passengerDAO.getPassegerInfo().getMoney()) {
            System.out.println("Passenger doesnt have enough money");
            returnMenu();
            return;
        }
        Seat s = seatDAO.asignSeat(route.get(flightIndex - 1));
        Ticket t = ticketDAO.buyFlightTicket(route.get(flightIndex - 1), s);
        System.out.println("This ticket was bought");
        System.out.println(t);
        returnMenu();
    }

    public void buyAllRouteTickets(List<Flight> route) {
        double total = flightDAO.getRoutePrice(route);
        if (total > passengerDAO.getPassegerInfo().getMoney()) {
            System.out.println("Passenger doesnt have enough money");
            returnMenu();
            return;
        }
        List<Seat> seats = new LinkedList<>();
        for (Flight f: route) {
            Seat s = seatDAO.asignSeat(f);
            seats.add(s);
        }
        List<Ticket> tickets = ticketDAO.buyRouteTickets(route,seats);
        System.out.println("Tickets bought successfully");
        returnMenu();
    }

    public void returnMenu() {
        System.out.println("Press  any number to return to the menu");
        scanner.hasNext();
        scanner.next();
    }

    public void seeMyTickets() {
        if (passengerDAO.getPassegerInfo().getTicket().size() == 0) {
            System.out.println("You dont have tickets");
        }
        for (Ticket t: passengerDAO.getPassegerInfo().getTicket()) {
            System.out.println("Leaving: " + t.getFlight().getLeaving().getName());
            System.out.println("GoingTo: " + t.getFlight().getGoingTo().getName());
            System.out.println("Seat number: " + t.getSeat().getNumber());
            System.out.println("----------------------------------------------------");
        }
        returnMenu();
    }
}
