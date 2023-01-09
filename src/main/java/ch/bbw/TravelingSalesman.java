package ch.bbw;

import java.util.ArrayList;

public class TravelingSalesman {
    private static ArrayList<City> cities;

    //TODO implement method
    public static Travel calculateThreeShortestRoutes(int startId, ArrayList<City> cityList) {
        cities = cityList;
        return simulateAnnealing(10, 10000, 0.9995, startId);
    }

    public static City findCity(int id) {
        return cities.stream().filter(x -> x.getId() == id).findFirst().get();
    }

    public static Travel simulateAnnealing(double startingTemperature, int numberOfIterations, double coolingRate, int startId) {
        double t = startingTemperature;
        Travel travel = new Travel(cities);
        double bestDistance = travel.getDistance(startId);
        System.out.println("Initial distance of travel: " + bestDistance);
        Travel bestSolution = travel;
        Travel currentSolution = bestSolution;

        for (int i = 0; i < numberOfIterations; i++) {
            if (t > 0.1) {
                currentSolution.swapCities();
                double currentDistance = currentSolution.getDistance(startId);
                if (currentDistance < bestDistance) {
                    bestDistance = currentDistance;
                } else if (Math.exp((bestDistance - currentDistance) / t) < Math.random()) {
                    currentSolution.revertSwap();
                }
                t *= coolingRate;
            } else {
                continue;
            }
        }
        return bestSolution;
    }
}
