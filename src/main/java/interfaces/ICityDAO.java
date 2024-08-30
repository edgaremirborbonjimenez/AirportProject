package interfaces;

import airport.City;

import java.util.List;

public interface ICityDAO {
    public List<City> getCities()throws Exception;
    public void setCities(List<City> cities);
}
