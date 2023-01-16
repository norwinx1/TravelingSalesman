package ch.bbw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader fileReader = new FileReader("data.json");
        Type listType = new TypeToken<ArrayList<City>>() {
        }.getType();
        ArrayList<City> cities = new Gson().fromJson(fileReader, listType);
        cities.forEach(x -> System.out.println(x.toString()));

        LinkedList<Travel> solutions = TravelingSalesman.calculateThreeShortestRoutes(1, cities);
        System.out.println(solutions);

        try {
            FileWriter myWriter = new FileWriter("solution.txt");
            for (Travel travel : solutions) {
                myWriter.write("Distance: " + travel.getDistance() + "\n");
                myWriter.write(travel + "\n\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}