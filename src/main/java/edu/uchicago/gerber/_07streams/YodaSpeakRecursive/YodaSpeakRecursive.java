package edu.uchicago.gerber._07streams.YodaSpeakRecursive;

import java.util.Arrays;

public class YodaSpeakRecursive {

    String ans;

    private void reverse(String[] words){

        if(words.length==0){
            return;
        }
        if(!ans.isEmpty()) ans += " ";
        ans += words[words.length-1];
        if(words.length == 1){
            return;
        }
        reverse(Arrays.copyOf(words, words.length - 1));
    }

    public String reverseString(String sentence){


        ans = "";
        reverse(sentence.split(" "));

        return ans;
    }




}
