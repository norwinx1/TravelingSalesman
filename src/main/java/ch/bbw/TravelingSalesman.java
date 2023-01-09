package ch.bbw;

import java.util.ArrayList;

public class TravelingSalesman {
    private static ArrayList<City> cities;

    public static Travel calculateThreeShortestRoutes(int startId, ArrayList<City> cityList) {
        City startCity = cityList.stream().filter(x -> x.getId() == startId).findFirst().get();
        cityList.removeIf(x -> x.getId() == startId);
        cities = cityList;
        cities.add(0, startCity);
        return simulateAnnealing(10, 10000, 0.9995);
    }

    public static City findCity(int id) {
        return cities.stream().filter(x -> x.getId() == id).findFirst().get();
    }

    public static Travel simulateAnnealing(double startingTemperature, int numberOfIterations, double coolingRate) {
        double t = startingTemperature;
        Travel travel = new Travel(cities);
        double bestDistance = travel.getDistance();
        System.out.println("Initial distance of travel: " + bestDistance);
        Travel bestSolution = travel;
        Travel currentSolution = bestSolution;

        for (int i = 0; i < numberOfIterations; i++) {
            if (t > 0.1) {
                currentSolution.swapCities();
                double currentDistance = currentSolution.getDistance();
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
        System.out.println("Best distance of travel: " + bestDistance);
        return bestSolution;
    }
}
