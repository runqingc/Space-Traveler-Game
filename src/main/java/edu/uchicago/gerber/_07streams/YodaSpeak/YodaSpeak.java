package edu.uchicago.gerber._07streams.YodaSpeak;

public class YodaSpeak {



    public String reverseString(String sentence){
        String[] words = sentence.split(" ");
        StringBuilder ans= new StringBuilder();
        for(int i=words.length-1; i>=0; --i){
            if(ans.length()>0){
                ans.append(" ");
            }
            ans.append(words[i]);
        }
        return ans.toString();
    }

}
