package ch.bbw;

import java.util.ArrayList;

public class Travel {
    private ArrayList<City> travel;
    private ArrayList<City> previousTravel = new ArrayList<>();

    public Travel(ArrayList<City> cities) {
        travel = cities;
    }

    private int generateRandomIndex() {
        int r = 0;
        while (r == 0) {
            r = (int) (Math.random() * travel.size());
        }
        return r;
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

    public int getDistance() {
        int distance = 0;
        for (int index = 0; index < travel.size(); index++) {
            City starting = travel.get(index);
            City destination;
            if (index + 1 < travel.size()) {
                destination = travel.get(index + 1);
            } else {
                destination = travel.get(0);
            }
            distance += starting.distanceToCity(destination);
        }
        return distance;
    }

    @Override
    public String toString() {
        return travel.toString();
    }
}
