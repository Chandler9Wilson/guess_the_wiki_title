package com.example;

import java.util.Scanner;

public class guessingGame {
    private int numberOfGuesses;
    private static String summaryWrapperText = "Given the following article summary: ";
    private static String guessWrapperText = "\nThe wiki title is?: ";
    private wikiArticle article;
    public boolean isGameOver = false;
    private String filteredTitle;
    private int points;
    private String currentGuess;
    //method filter out the title from summary
    //
    public guessingGame()
    {
        this.numberOfGuesses = 3;
        this.article = new wikiArticle();
        this.points = 100;
    }

    public void playGame(Scanner input)
    {
        filterSummary();

        while(!isGameOver)
        {
            System.out.println(summaryWrapperText + this.article.summaryExtract);
            System.out.print(guessWrapperText);

            currentGuess = input.nextLine();

            isGuessCorrect(currentGuess);
        }
    }

    // Filter the title out of the summary
    public void filterSummary()
    {
        char[] tempFilter = new char[this.article.normalizedTitle.length()];
        if(this.article.summaryExtract.contains(this.article.normalizedTitle))
        {
            
            for(int i = 0; i < this.article.normalizedTitle.length();i++)
            {
                tempFilter[i] = '-';
            }

            this.filteredTitle = new String(tempFilter);
            this.article.summaryExtract.replace(this.article.normalizedTitle, this.filteredTitle);
        }
    }



    public boolean isGuessCorrect(String guess)
    {
        this.numberOfGuesses--;
        if(guess.equals(this.article.normalizedTitle))
        {
            System.out.println("Your guess was correct!!! \tScore: "+ this.points);
            isGameOver = true;
            return true;
        };

        if(numberOfGuesses == 0)
        {
            isGameOver = true;
            System.out.println("The title was: " + this.article.normalizedTitle);
        }
        else {
            System.out.println("Incorrect\nNumber of guesses remaining:" + numberOfGuesses +"\tScore: " + this.pointsCalculator());
        }

        return false;
    }

    private int pointsCalculator()
    {
        this.points -= 20;
        return points;    
    }

    public int pointsGetter()
    {
        return this.points;
    }
}
