package ch.bbw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String SOURCE_FILE = "data.json";
    private static final String TARGET_FILE = "solution.txt";
    private static final int START_ID = 1;

    public static void main(String[] args) throws FileNotFoundException {
        //Read data file
        FileReader fileReader = new FileReader(SOURCE_FILE);
        Type listType = new TypeToken<ArrayList<City>>() {
        }.getType();
        Gson gson = new Gson();
        ArrayList<City> cities = gson.fromJson(fileReader, listType);
        cities.forEach(x -> System.out.println(x.toString()));

        //Use algorithm to determine the shortest routes
        List<Travel> solutions = TravelingSalesman.calculateThreeShortestRoutes(START_ID, cities);
        System.out.println("See generated file for more solutions");

        //Write the solutions to the file
        try {
            FileWriter myWriter = new FileWriter(TARGET_FILE);
            for (Travel travel : solutions) {
                myWriter.write("Distance: " + travel.determineDistance() + "\n");
                myWriter.write(travel + "\n\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}