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

    /**
     * Swaps two random cities with each other
     */
    public void swapCities() {
        int a = generateRandomIndex();
        int b = generateRandomIndex();
        previousTravel = new ArrayList<>(travel);
        City x = travel.get(a);
        City y = travel.get(b);
        travel.set(a, y);
        travel.set(b, x);
    }

    /**
     * Reverts the swap made by {@link Travel#swapCities() swapCities}
     */
    public void revertSwap() {
        travel = previousTravel;
    }

    /**
     * Calculates the complete distance of this travel
     * @return the distance from <code>cities[0]</code> to <code>cities[cities.size()-1]</code>
     */
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

    @Override
    public Travel clone() {
        return new Travel(new ArrayList<>(this.travel));
    }
}
