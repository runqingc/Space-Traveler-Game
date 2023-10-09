package edu.uchicago.gerber._03objects.P8_8;

public class Driver {

    public static void main(String[] args){
        VotingMachine votingMachine = new VotingMachine();

        votingMachine.voteDemocrat();
        votingMachine.voteRepublican();
        votingMachine.voteRepublican();
        votingMachine.voteRepublican();
        votingMachine.voteDemocrat();
        votingMachine.voteDemocrat();
        votingMachine.voteDemocrat();
        votingMachine.voteRepublican();
        votingMachine.voteRepublican();
        votingMachine.voteDemocrat();
        votingMachine.voteDemocrat();
        votingMachine.voteDemocrat();
        votingMachine.voteRepublican();
        votingMachine.voteRepublican();
        votingMachine.voteDemocrat();
        votingMachine.getTally();
    }

}
