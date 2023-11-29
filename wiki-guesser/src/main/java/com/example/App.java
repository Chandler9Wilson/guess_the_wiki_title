package com.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        guesserCLI cli = new guesserCLI();
        Scanner input = new Scanner(System.in);
        cli.startGame(input);
        input.close();
        //wikiArticle newArticle = new wikiArticle();
    }
}
