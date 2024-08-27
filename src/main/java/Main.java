import presentation.Menu;

public class Main {
    public static void main(String[] args) {
/*
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

        Airport airport = new Airport();
        Flight flight1 = new Flight();
        flight1.setLeaving(city);
        flight1.setGoingTo(city2);
        Flight flight2 = new Flight();
        flight2.setLeaving(city2);
        flight2.setGoingTo(city3);
        Flight flight3 = new Flight();
        flight3.setLeaving(city3);
        flight3.setGoingTo(city4);
        Flight flight4 = new Flight();
        flight4.setLeaving(city4);
        flight4.setGoingTo(city5);
        Flight flight5 = new Flight();
        flight5.setLeaving(city);
        flight5.setGoingTo(city3);
        Flight flight6 = new Flight();
        flight6.setLeaving(city);
        flight6.setGoingTo(city6);

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

        List<List<Flight>> flights = airport.getRoute(city,city5,4,0);
        int cont =1;
        for (List<Flight> flightList : flights) {
            System.out.println("Flight "+cont+" ---------------------------------");
            cont++;
            if (flightList != null) {
                for (Flight flight : flightList) {
                    if (flight != null) {
                        System.out.println(flight.getLeaving().getName() + " : " + flight.getGoingTo().getName());
                    }else{
                        System.out.println(flight);
                    }
                }
            }
            System.out.println();
        }*/
        try {
            Menu menu = new Menu();
            menu.displayMenu();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
