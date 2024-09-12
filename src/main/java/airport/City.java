package airport;

import utils.ECity;

import java.util.Objects;

public class City {
    private ECity city;

    public City() {

    }
    public City(ECity city) {
        this.city = city;
    }

    public String getName() {
        return city.getName();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(this.city.getName(), city.city.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(city.getName());
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + city.getName() + '\'' +
                '}';
    }
}
