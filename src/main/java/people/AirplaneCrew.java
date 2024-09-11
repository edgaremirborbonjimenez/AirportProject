package people;

import airport.Airplane;

import java.util.Date;

public abstract class AirplaneCrew extends Person {
    protected Date dateStartedWorking;
    protected int amountOfTripsDone;
    protected Airplane airplane;

    public AirplaneCrew() {

    }

    public AirplaneCrew(String firstName, String lastName, Date birthDate, Date dateStartedWorking,Airplane airplane) {
        super(firstName,lastName,birthDate);
        this.dateStartedWorking = dateStartedWorking;
        this.amountOfTripsDone = 0;
        this.airplane = airplane;
    }

    public Date getDateStartedWorking() {
        return dateStartedWorking;
    }
    public void setDateStartedWorking(Date dateStartedWorking) {
        this.dateStartedWorking = dateStartedWorking;
    }
    public int getAmountOfTripsDone() {
        return amountOfTripsDone;
    }

    public void setAmountOfTripsDone(int amountOfTripsDone) {
        this.amountOfTripsDone = amountOfTripsDone;
    }

    public void addOneMoreTrip() {
        this.amountOfTripsDone++;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public abstract void anounceMessage();
}
