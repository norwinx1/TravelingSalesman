package ch.bbw;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TravelingSalesman {
    private static ArrayList<City> cities;

    public static Set<Travel> calculateThreeShortestRoutes(int startId, ArrayList<City> cityList) {
        City startCity = cityList.stream().filter(x -> x.getId() == startId).findFirst().get();
        cityList.removeIf(x -> x.getId() == startId);
        cities = cityList;
        cities.add(0, startCity);
        return simulateAnnealing(10000, 0.9999);
    }

    public static Set<Travel> simulateAnnealing(int iterations, double rate) {
        double t = 10;
        Travel travel = new Travel(cities);
        double bestDistance = travel.getDistance();
        Set<Travel> bestTravels = new HashSet<>();

        for (int i = 0; i < iterations; i++) {
            if (t > 0.1) {
                travel.swapCities();
                double currentDistance = travel.getDistance();
                if (currentDistance < bestDistance) {
                    bestDistance = currentDistance;
                    bestTravels.add(travel.clone());
                } else if (Math.exp((bestDistance - currentDistance) / t) < Math.random()) {
                    travel.revertSwap();
                }
                t *= rate;
            } else {
                continue;
            }
        }

        System.out.println("Best distance of travel: " + bestDistance + " with route: " + travel);
        return bestTravels.stream().sorted(Comparator.comparing(Travel::getDistance)).limit(3).collect(Collectors.toSet());
    }
}
