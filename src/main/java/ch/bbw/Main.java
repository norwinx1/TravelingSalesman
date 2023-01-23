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
    final static String SOURCE_FILE = "data.json";
    final static String TARGET_FILE = "solution.txt";

    public static void main(String[] args) throws FileNotFoundException {
        //Read data file
        FileReader fileReader = new FileReader(SOURCE_FILE);
        Type listType = new TypeToken<ArrayList<City>>() {
        }.getType();
        Gson gson = new Gson();
        ArrayList<City> cities = gson.fromJson(fileReader, listType);
        cities.forEach(x -> System.out.println(x.toString()));

        //Use algorithm to determine the shortest routes
        List<Travel> solutions = TravelingSalesman.calculateThreeShortestRoutes(1, cities);

        //Write the solutions to the file
        try {
            FileWriter myWriter = new FileWriter(TARGET_FILE);
            for (Travel travel : solutions) {
                new Gson().toJson(travel);
                myWriter.write("Distance: " + travel.getDistance() + "\n");
                myWriter.write(travel + "\n\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}