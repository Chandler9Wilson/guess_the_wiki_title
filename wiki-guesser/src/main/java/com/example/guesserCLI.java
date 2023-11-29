package com.example;

import java.util.Scanner;

public class guesserCLI {
    private static String introText = "Welcome player! This is Guess the Wiki Title!\n\n"+
        "In this game, you will have to figure out what "+
        "the title \nof the Wikipedia page is from a two "+
        "sentence summary.\n\nAre you ready to play? (Y/N) or Q to quit program";
    private static String scoreWrapperText = " is your final score.";
    private boolean isUserReady;
    private boolean isGameInstanceOver;

    public guesserCLI() {
        isUserReady = false;
        isGameInstanceOver = false;
    }

    public void startGame(Scanner input) {
        while (isGameInstanceOver == false) {
            String ready = "";
            boolean isValidInput = false;
            
            while (isUserReady == false) {
                System.out.println(introText);
                ready = input.nextLine();
                isValidInput = false;
                while (isValidInput == false) {
                    if (ready.equalsIgnoreCase("Y")) {
                        System.out.println("\nThe game will start\n");
                        isUserReady = true;
                        isValidInput = true;
                        // TODO
                        //guessingGame game = new guessingGame();
                    } else if (ready.equalsIgnoreCase("N")) {
                        System.out.println("The game will not start\n");
                        isValidInput = true;
                    } else if (ready.equalsIgnoreCase("Q")) {
                        System.exit(0);
                    } else {
                        System.out.println("INVALID INPUT, Please input Y or N\n-----------------------------\n");
                        break;
                    }
                }
                    //Code for game logic  
                    
                    System.out.println(game.score + scoreWrapperText + "\n-----------------------------\n");


                    isUserReady = false;
            }

        }
    }
}
