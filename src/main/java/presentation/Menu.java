package presentation;

import airport.*;
import exception.FlightException;
import implementation.*;
import interfaces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import people.Passenger;
import utils.Model;
import utils.customLinkedList.CustomLinkedList;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class Menu {

    private final IFlightDAO flightDAO;
    private final ICityDAO cityDAO;
    private final ITicketDAO ticketDAO;
    private final ISeatDAO seatDAO;
    private final IPassengerDAO passengerDAO;
    private final Scanner scanner;
    private final Passenger passenger;
    private static final Logger logger = LogManager.getLogger("Airport");

    public Menu() {

        flightDAO = FlightDAO.getInstance();
        cityDAO = CityDAO.getInstance();
        ticketDAO = TicketDAO.getInstance();
        seatDAO = SeatDAO.getInstance();
        passengerDAO = PassengerDAO.getInstance();
        scanner = new Scanner(System.in);
        passenger = Model.getInstance().getPassenger();

    }

    static {
        System.out.println("Welcome to Airport Menu");
    }

    public void displayMenu() {
        try {
            do {
                System.out.println("What do you want to do?");
                System.out.println("1.- Find a flights to go to somewhere");
                System.out.println("2.- See my tickets");
                System.out.println("Press 3 to exit");


                if (scanner.hasNextInt()) {
                    int option = scanner.nextInt();

                    if (option == 1) {
                        selectDestination(
                                cities -> cities!=null,
                                name-> System.out.println(name),
                                city -> city.getName());
                    } else if (option == 2) {
                        seeMyTickets();
                    } else if (option == 3) {
                        scanner.close();
                        return;
                    } else {
                        System.out.println("Please select a valid option");
                    }
                } else {
                    scanner.next();
                    System.out.println("Please select a valid option");
                    System.out.println();
                }
            } while (true);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Lambda
    public void selectDestination(Predicate<List<City>> validator, Consumer<String> block, Function<City,String> getName) {

        int leavingOpt;
        int goingToOpt;
        do {
            try {
                System.out.println("Select where you are leaving");
//                for (int i = 0; i < cityDAO.getCities().size(); i++) {
//                    System.out.println((i + 1) + ".-" + cityDAO.getCities().get(i).getName());
//                }
                if(validator.test(cityDAO.getCities())){
                for (int i = 0; i < cityDAO.getCities().size(); i++) {
                    String cityName = getName.apply(cityDAO.getCities().get(i));
                    System.out.print(i+1+".- ");
                        block.accept(cityName);
                    }
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

                showRoutes(
                                flightDAO.getRoute(cityDAO.getCities().get(leavingOpt - 1),
                                cityDAO.getCities().get(goingToOpt - 1)),
                                (opt,flights)->opt>flights.size()||opt<=0,
                                (opt,flights)->{
                                    System.out.println("You selected the option: "+opt);
                                    System.out.println("and there only :"+flights.size()+" options");
                                    System.out.println("Please Select a valid option");
                                });
                break;

            } catch (Exception e) {
                System.err.println(e.getMessage() + " Talk to the IT Department");
            }

        } while (true);
    }

    //Lambda
    public void showRoutes(List<List<Flight>> routes,BiPredicate<Integer,List<List<Flight>>> biValidator,BiConsumer<Integer,List<List<Flight>>> biBlock)throws Exception {
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

            //if (option > routes.size() || option < 0) {
            if(biValidator.test(option,routes)){
                //System.out.println("Please select a valid option");
                biBlock.accept(option,routes);
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
        //Scanner scanner = new Scanner(System.in);
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

    //Lambda Function
    public Seat selectSeat(Flight flight, Predicate<Passenger> validator) {
        if (flight== null){
            logger.error("Flight is null");
            return null;
        }
        while (true){
            flight.showSeats();
            System.out.println("Enter a seat number");
            if(!scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please select a valid option");
                System.out.println();
                continue;
            }
            int option = scanner.nextInt();

            if(option>flight.getSeats().getSize()){
                System.out.println("Please select a valid option");
                System.out.println();
                continue;
            }
            Seat seatReturned = flight.getSeats().getIndex(option);
            //if(seatReturned.getPassenger() != null){
            if(validator.test(seatReturned.getPassenger())){
                System.out.println("Seat is not available, please select another seat");
                continue;
            }
            return seatReturned;
        }
    }

    public void buyFlight(int flightIndex,List<Flight> route) {
        if (route.get(flightIndex - 1).getPrice() > passengerDAO.getPassegerInfo().getMoney()) {
            System.out.println("Passenger doesnt have enough money");
            returnMenu();
            return;
        }
            Seat seatSelected = selectSeat(route.get(flightIndex - 1),p->p!=null);

            if (seatSelected == null) {
                return;
            }

        try {
            Seat s = seatDAO.asignSeat(route.get(flightIndex - 1), this.passenger,seatSelected);
            Ticket t = ticketDAO.buyFlightTicket(route.get(flightIndex - 1), s,this.passenger);
            System.out.println("This ticket was bought");
            returnMenu();
        }catch (FlightException e){
            System.out.println(e.getMessage());
        }
    }

    public void buyAllRouteTickets(List<Flight> route) {
        double total = flightDAO.getRoutePrice(route);
        if (total > passengerDAO.getPassegerInfo().getMoney()) {
            System.out.println("Passenger doesnt have enough money");
            returnMenu();
            return;
        }

        Map<Flight,Seat> seats = new HashMap<>();

        for(Flight flight : route){
            Seat seatSelected = selectSeat(flight,p->p!=null);
            if (seatSelected == null) {
                continue;
            }
            seats.put(flight,seatSelected);
        }
        //LAMBDA
        try{
            seats.forEach((flight,seat)->{
                Seat s = seatDAO.asignSeat(flight,this.passenger,seat);
                ticketDAO.buyFlightTicket(flight,s,this.passenger);
            });
        }catch (FlightException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Tickets bought successfully");
        returnMenu();
    }

    public void returnMenu() {
        System.out.println("Press  any number to return to the menu");
        scanner.hasNext();
        scanner.next();
    }

    public void seeMyTickets() {
        if (this.passenger.getTicket().isEmpty()) {
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
