package ch.bbw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader fileReader = new FileReader("data.json");
        Type listType = new TypeToken<ArrayList<City>>() {
        }.getType();
        ArrayList<City> cities = new Gson().fromJson(fileReader, listType);
        cities.forEach(x -> System.out.println(x.toString()));

        Travel solution1 = TravelingSalesman.calculateThreeShortestRoutes(1, cities);
        System.out.println(solution1);

        try {
            FileWriter myWriter = new FileWriter("solution.txt");
            myWriter.write(solution1.toString());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}