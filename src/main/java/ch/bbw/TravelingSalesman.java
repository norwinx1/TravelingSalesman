package ch.bbw;

import java.util.ArrayList;
import java.util.LinkedList;

public class TravelingSalesman {
    private static ArrayList<City> cities;

    public static LinkedList<Travel> calculateThreeShortestRoutes(int startId, ArrayList<City> cityList) {
        City startCity = cityList.stream().filter(x -> x.getId() == startId).findFirst().get();
        cityList.removeIf(x -> x.getId() == startId);
        cities = cityList;
        cities.add(0, startCity);
        return simulateAnnealing(10000, 0.9999);
    }

    public static City findCity(int id) {
        return cities.stream().filter(x -> x.getId() == id).findFirst().get();
    }

    public static LinkedList<Travel> simulateAnnealing(int iterations, double rate) {
        double t = 10;
        Travel travel = new Travel(cities);
        double bestDistance = travel.getDistance();
        System.out.println("Initial distance of travel: " + bestDistance);
        Travel currentSolution = travel;
        LinkedList<Travel> bestTravels = new LinkedList<>();

        for (int i = 0; i < iterations; i++) {
            if (t > 0.1) {
                currentSolution.swapCities();
                double currentDistance = currentSolution.getDistance();
                if (currentDistance < bestDistance) {
                    bestDistance = currentDistance;
                    //TODO correct implementation
                    if (bestTravels.size() == 3) {
                        bestTravels.pollFirst();
                    }
                    bestTravels.add(currentSolution);
                } else if (Math.exp((bestDistance - currentDistance) / t) < Math.random()) {
                    currentSolution.revertSwap();
                }
                t *= rate;
            } else {
                continue;
            }
        }
        System.out.println("Best distance of travel: " + bestDistance);
        return bestTravels;
    }
}
