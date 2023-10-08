package edu.uchicago.gerber._02arrays;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class P7_5 {


    // define fields
    private int numRow = -1;

    private String filePath;

    // define getter & setter

    public int getNumRow() {
        return numRow;
    }

    public void setNumRow(int numRow) {
        this.numRow = numRow;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    // define constructor

    public P7_5() {
    }

    public P7_5(String filePath) {
        this.filePath = filePath;
    }


    // define member functions
    int numberOfRows(){
        if(numRow!=-1){
            return numRow;
        }
        int i=0;
        String inputFile = filePath;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            while (reader.readLine() != null) {
                ++i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numRow = i;
    }

    int numberOfFields(int row){
        String inputFile = filePath;
        int cnt = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            int r=0;
            String line;
            while ((line = reader.readLine() )!= null && r<row-1) {
                ++r;
            }
            boolean numQuote=true;
            for(int i = 0; i< Objects.requireNonNull(line).length(); ++i){
                if(line.charAt(i)=='\"'){
                    numQuote = !numQuote;
                }else if(line.charAt(i)==',' && numQuote){
                    ++cnt;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ++cnt;

    }

    String field(int row, int column){
        int numField = numberOfFields(row);
        String inputFile = filePath;
        ArrayList<StringBuilder> fieldList = new ArrayList<>();
        if(column>numField){
            System.out.println("column exceeds the max column in that row.");
        }else{
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                int r=0;
                String line;

                while ((line = reader.readLine() )!= null && r<row-1) {
                    ++r;
                }
                StringBuilder stringBuilder = new StringBuilder();
                boolean numQuote=true;
                for(int i = 0; i< Objects.requireNonNull(line).length(); ++i){
                    if(line.charAt(i)=='\"'){
                        numQuote = !numQuote;
                        stringBuilder.append(line.charAt(i));
                    }else if(line.charAt(i)==',' && numQuote){
                        fieldList.add(stringBuilder);
                        stringBuilder = new StringBuilder();
                    }else{
                        stringBuilder.append(line.charAt(i));
                    }
                }
                fieldList.add(stringBuilder);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fieldList.get(column-1).toString();
    }

    // define main function
    public static void main(String[] args){
        P7_5 csvReader = new P7_5("example.csv");

        System.out.println("The number of rows: " + csvReader.numberOfRows());
        System.out.println("The number of fields in column 5: " +csvReader.numberOfFields(5));
        System.out.println("Read all fields: ");
        for(int i=1; i<=csvReader.numberOfRows(); ++i){
            for(int j=1; j<=csvReader.numberOfFields(i); ++j){
                System.out.print(csvReader.field(i, j)+" | ");
            }
            System.out.println();
        }

    }
}


