package edu.uchicago.gerber._03objects.P8_14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Driver {

    public class CountryReader {

        public static void main(String[] args) {
            String filePath = "countries.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts.length == 3) {
                        String countryName = parts[0];
                        String population = parts[1];
                        String area = parts[2];
                        System.out.println("Name: " + countryName + "; Population: " + population + "; Area: " + area);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
