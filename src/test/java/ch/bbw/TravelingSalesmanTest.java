package ch.bbw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TravelingSalesmanTest {

    @Test
    void testDistanceToCity() {
        City zurich = new City(1, "Zürich", 1, 1);
        City arosa = new City(2, "Arosa", 400, 105);
        Assertions.assertEquals(412, ((int) zurich.distanceToCity(arosa)));
        Assertions.assertEquals(412, ((int) arosa.distanceToCity(zurich)));
    }

    @Test
    void testGetDistance() {
        City zurich = new City(1, "Zürich", 1, 1);
        City arosa = new City(2, "Arosa", 400, 105);
        ArrayList<City> cities = new ArrayList<>(List.of(zurich, arosa));
        Travel travel = new Travel(cities);
        Assertions.assertEquals(824, travel.getDistance());
    }

}
