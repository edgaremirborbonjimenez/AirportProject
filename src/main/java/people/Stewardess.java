package people;

import airport.Airplane;
import interfaces.IPassenger;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Stewardess extends AirplaneCrew implements IPassenger {

    String[] personalStrengths;


    public Stewardess(){}

    @Override
    public void anounceMessage() {
        System.out.println("There is any help you need?");
    }

    public Stewardess(String firstName, String lastName, Date birthDate, Date dateStartedWorking,String[] personalStrengths, Airplane airplane){
        super(firstName, lastName, birthDate, dateStartedWorking,airplane);
        this.personalStrengths = personalStrengths;
    }
    public String[] getPersonalStrengths() {
        return personalStrengths;
    }

    public void setPersonalStrengths(String[] personalStrengths) {
        this.personalStrengths = personalStrengths;
    }

    @Override
    public String toString() {
        return "Stewardess{" +
                "personalStrengths=" + Arrays.toString(personalStrengths) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Stewardess that = (Stewardess) o;
        return Objects.deepEquals(personalStrengths, that.personalStrengths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(personalStrengths));
    }

    @Override
    public void saySomething() {
        System.out.println("Please stay sit, if you need help just ask me");
    }
}
