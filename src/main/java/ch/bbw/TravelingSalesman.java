package ch.bbw;

import java.util.*;
import java.util.stream.Collectors;

public class TravelingSalesman {
    private static final double RATE = 0.9999;
    private static final int ITERATIONS = 10000;
    private static ArrayList<City> cities;

    /**
     * Calculates the three shortest routes it could find with the optimizing algorithm. Not completely consistent due to finding backward routes and random indexing.
     * @param startId the id of the city from where to start the journey
     * @param cityList all cities with names and coordinates
     * @return the three shortest routes (backward routes not separately included)
     */
    public static List<Travel> calculateThreeShortestRoutes(int startId, ArrayList<City> cityList) {
        //Move startCity to index 0
        City startCity = cityList.stream().filter(x -> x.getId() == startId).findFirst().get();
        cityList.removeIf(x -> x.getId() == startId);
        cities = cityList;
        cities.add(0, startCity);

        return simulateAnnealing(ITERATIONS, RATE);
    }

    /**
     * The simulate-annealing algorithm adjusted for cities
     * @param iterations number of iterations to run the algorithm
     * @param rate how accurately the algorithm should optimize
     * @return the three shortest routes (backward routes not included)
     */
    public static List<Travel> simulateAnnealing(int iterations, double rate) {
        double t = 10;
        Travel travel = new Travel(cities);
        double bestDistance = travel.determineDistance();
        Set<Travel> bestTravels = new HashSet<>();

        for (int i = 0; i < iterations; i++) {
            if (t > 0.1) {
                travel.swapCities();
                double currentDistance = travel.determineDistance();
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
        return bestTravels.stream().sorted(Comparator.comparing(Travel::determineDistance)).limit(3).collect(Collectors.toList());
    }
}
