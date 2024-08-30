package people;

import airport.Airplane;
import interfaces.IPassenger;

import java.util.Date;
import java.util.Objects;

public class Pilot extends AirplaneCrew implements IPassenger {

    String range;

    public Pilot() {}

    @Override
    public void anounceMessage() {
        System.out.println("We are going to start the flight please stay sit");
    }

    public Pilot(String firstName, String lastName, Date birthDate, Date dateStartedWorking, String range, Airplane airplane) {
        super(firstName, lastName, birthDate, dateStartedWorking,airplane);
        this.range = range;
    }

    public String getRange() {
        return range;
    }
    public void setRange(String range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "range='" + range + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pilot pilot = (Pilot) o;
        return Objects.equals(range, pilot.range);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), range);
    }

    @Override
    public void saySomething() {
        System.out.println("We are going to start the flight please stay sit");
    }
}
