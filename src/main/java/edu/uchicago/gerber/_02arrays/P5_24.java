package edu.uchicago.gerber._02arrays;



public class P5_24 {

    public static int letterMapping(char c){
        if(c=='I'){
            return 1;
        }else if(c=='V'){
            return 5;
        }else if(c=='X'){
            return 10;
        }else if(c=='L'){
            return 50;
        }else if(c=='C'){
            return 100;
        }else if(c=='D'){
            return 500;
        }else{
            return 1000;
        }
    }

    public static int convertRoman(String str){
        int total=0;
        int i=0;
        while(!str.isEmpty()){
            if(str.length()==1 || (i+1<str.length()-1 && str.charAt(i)>=str.charAt(i+1))){
                total += letterMapping(str.charAt(i));
                ++i;
            }else{
                int difference = letterMapping(str.charAt(i+1)) - letterMapping(str.charAt(i));
                total += difference;
                i+=2;
            }
        }
        return total;

    }

    public static void main(String[] args){
        assert (convertRoman("V")==5);
        assert (convertRoman("MCMLXXVIII")==1978);
        assert (convertRoman("I") == 1);
        assert (convertRoman("V") == 5);
        assert (convertRoman("X") == 10);
        assert (convertRoman("L") == 50);
        assert (convertRoman("C") == 100);
        assert (convertRoman("D") == 500);
        assert (convertRoman("M") == 1000);

        // Test complex numbers
        assert (convertRoman("IV") == 4);
        assert (convertRoman("IX") == 9);
        assert (convertRoman("XL") == 40);
        assert (convertRoman("XC") == 90);
        assert (convertRoman("CD") == 400);
        assert (convertRoman("CM") == 900);

        // Test mixed numbers
        assert (convertRoman("MCMLXXVIII") == 1978);
        assert (convertRoman("MCDXLIV") == 1444);
        assert (convertRoman("MMMCMXCIX") == 3999);

        System.out.println("All tests passed!");
    }


}
