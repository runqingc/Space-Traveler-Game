package edu.uchicago.gerber._07streams.P13_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Phone {


    private final String[] PAD = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    private Set<String> validWords = new TreeSet<>();

    public Phone() {
        try {
            validWords.addAll(
                    Files.readAllLines(Paths.get("./src/main/java/edu/uchicago/gerber/_07streams/P13_3/words.txt"))
                            .stream() // Convert the list to a stream
//                            .filter(line -> line.length() > 1) // Filter lines to include only those with more than 1 character
                            .map(String::toUpperCase) // Convert each line to uppercase
                            .collect(Collectors.toList()) // Collect the results back into a list
            );
            System.out.println("Valid words load success.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // find all possible char combination for numbers
    // P13.2
    public void convertNumber(String number, StringBuilder current, ArrayList<String> res){

        if(number.isEmpty()){
            // Assuming you want to add the current accumulated characters as a valid word
            if(validWords.contains(current.toString())){
                res.add(current.toString());
//                System.out.println(current);
            }
            return;
        }
        for(char c : PAD[number.charAt(0) - '0'].toCharArray()){
            current.append(c); // Append the character to current
            convertNumber(number.substring(1), current, res);
            current.deleteCharAt(current.length() - 1); // Remove the last character to backtrack
        }

    }

    public void convertPhoneNumber(Set<String> ans, String phoneNumber ){
        // if phoneNumber itself has valid words, add result in ans
        // for every i as break point
        // convert 0->i, put result in l_res
        // convert i+1->end-1, put result in r_res (l, r substring shall all have least 1 char)
        // merge l and r if both of them are not empty, add result to ans
        ArrayList<String> res = new ArrayList<>();


        StringBuilder stringBuilder = new StringBuilder();
        convertNumber(phoneNumber, stringBuilder, res);

        if(!res.isEmpty())ans.addAll(res);
        for(int i=1; i<phoneNumber.length(); ++i){
            Set<String> l_res = new HashSet<>();
            convertPhoneNumber(l_res, phoneNumber.substring(0,i));
            Set<String> r_res = new HashSet<>();
            convertPhoneNumber(r_res, phoneNumber.substring(i));
            if(!l_res.isEmpty() && !r_res.isEmpty()){
                for(String l_str: l_res){
                    for(String r_str: r_res){
                        ans.add(l_str+" "+r_str);
                    }
                }

            }
        }


    }

    public void convertEntry(String phoneNumber){

        Set<String> ans = new HashSet<>();
        convertPhoneNumber(ans, phoneNumber);
        for(String str: ans){
            System.out.println(str);
        }

    }


}
