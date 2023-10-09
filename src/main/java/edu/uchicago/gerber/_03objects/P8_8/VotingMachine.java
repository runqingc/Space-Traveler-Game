package edu.uchicago.gerber._03objects.P8_8;

public class VotingMachine {

    int democrat;

    int republican;

    public VotingMachine() {
        democrat = 0;
        republican = 0;
    }

    public void votingMachine() {
        democrat = 0;
        republican = 0;
    }

    public void voteDemocrat(){
        ++democrat;
    }

    public void  voteRepublican(){
        ++republican;
    }

    public void getTally(){
        System.out.println("The votes Democrat get is: " + democrat);
        System.out.println("The votes Republican get is: " + republican);
    }
}
