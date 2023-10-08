package edu.uchicago.gerber._02arrays;

import java.io.*;

public class E7_4 {

    public static void main(String[] args){
        String inputFile = "mary_input.txt";
        String outputFile = "mary_output.txt";
        int i=1;
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write("/*"+i+"*/");
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
