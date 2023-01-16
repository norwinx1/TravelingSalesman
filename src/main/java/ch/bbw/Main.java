package ch.bbw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader fileReader = new FileReader("data.json");
        Type listType = new TypeToken<ArrayList<City>>() {
        }.getType();
        ArrayList<City> cities = new Gson().fromJson(fileReader, listType);
        cities.forEach(x -> System.out.println(x.toString()));

        Set<Travel> solutions = TravelingSalesman.calculateThreeShortestRoutes(1, cities);

        try {
            FileWriter myWriter = new FileWriter("solution.txt");
            for (Travel travel : solutions.stream().sorted(Comparator.comparing(Travel::getDistance)).limit(3).toList()) {
                myWriter.write("Distance: " + travel.getDistance() + "\n");
                myWriter.write(travel + "\n\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}