package airport;

import people.Passenger;
import utils.TypeSeat;

import java.util.Objects;

public class Seat {
    private Passenger passenger;
    private boolean isSpecial;
    private TypeSeat typeSeat;
    private  int number;

    public Seat() {

    }

    public Seat(Passenger passenger, boolean isSpecial) {
        this.passenger = passenger;
        this.isSpecial = isSpecial;
    }
    public Passenger getPassenger() {
        return passenger;
    }
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    public boolean isSpecial() {
        return isSpecial;
    }
    public void setSpecial(boolean special) {
        isSpecial = special;
    }

    public TypeSeat getTypeSeat() {
        return typeSeat;
    }

    public void setTypeSeat(TypeSeat typeSeat) {
        this.typeSeat = typeSeat;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Seat: " +
                "passenger=" + passenger +
                ", isSpecial=" + isSpecial +
                ", typeSeat=" + typeSeat +
                ", number=" + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return isSpecial == seat.isSpecial && number == seat.number && Objects.equals(passenger, seat.passenger) && typeSeat == seat.typeSeat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passenger, isSpecial, typeSeat, number);
    }
}
