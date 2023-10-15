package edu.uchicago.gerber._03objects.P8_14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Driver {

    public static void main(String[] args) {
        String filePath = "P8_14/countries.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String countryName = parts[0];
                    long population = Long.parseLong(parts[1]);
                    double area = Double.parseDouble(parts[2]);
                    Country country= new Country(countryName, population, area);
                    System.out.println("Name: " + country.getName() + "; Population: " + country.getPopulation()
                            + "; Area: " + country.getArea()+ " ;Density: " + country.density());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
