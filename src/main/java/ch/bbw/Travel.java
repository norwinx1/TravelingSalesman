package ch.bbw;

import java.util.ArrayList;

public class Travel {
    private ArrayList<City> travel;
    private ArrayList<City> previousTravel = new ArrayList<>();

    public Travel(ArrayList<City> cities) {
        travel = cities;
    }

    private int generateRandomIndex() {
        return (int) (Math.random() * travel.size());
    }

    public void swapCities() {
        int a = generateRandomIndex();
        int b = generateRandomIndex();
        previousTravel = new ArrayList<>(travel);
        City x = travel.get(a);
        City y = travel.get(b);
        travel.set(a, y);
        travel.set(b, x);
    }

    public void revertSwap() {
        travel = previousTravel;
    }

    public int getDistance(int startId) {
        int distance = 0;
        for (int index = 0; index < travel.size(); index++) {
            City starting = findCity(startId);
            City destination;
            if (index + 1 < travel.size()) {
                destination = travel.get(index + 1);
            } else {
                destination = findCity(startId);
            }
            distance += starting.distanceToCity(destination);
        }
        return distance;
    }

    public City findCity(int id) {
        return travel.stream().filter(x -> x.getId() == id).findFirst().get();
    }

    @Override
    public String toString() {
        return travel.toString();
    }
}
