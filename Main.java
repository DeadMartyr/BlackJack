import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Random;
import java.util.Scanner;

import playingCards.*;
import gameBlackJack.*;
 

public class Main
{
	//Scanner Initialized
    public static Scanner userinput = new Scanner(System.in);
	
    //BlackJack game created
    public static BlackJack game = new BlackJack();
    
    //Just type in "{}" to run the program
    public static void main(String[] args) 
    		throws InterruptedException
    {
    	System.out.println("Type '1' to play BlackJack, type '2' to test the shuffling");
        String input = userinput.nextLine();
        System.out.println();
        
        if(input.equals("1"))
        {
        	game.runGame();
        }
        
        if(input.equals("2"))
        {
        	testBuildDeck();
        }
    }
    
    
    /*
    //Menu Methods:
    //Methods used for starting the actual game, utility kind of things
    
    //checkInput(String check, String thing)
        Stalls the program until the user inputs "check" and hits enter, 
        meant to divide up the program into different chunks showing off "thing"
    */
    public static void checkInput(String check, String thing)
    {
        System.out.println("");
        System.out.println("Type '" + check +"' and hit enter to test " + thing);
        while(true)
        {
            if(userinput.nextLine().equals(check))
            {
                break;
            }
        }
    }
    
    
    /*
    //Test Methods:
    //Methods used for testing the functionality of different methods and stuff
    
    //testDeckBuild()
        Runs a playback of the creation of a deck
    */
    public static void testBuildDeck()
    	throws InterruptedException
    {
    	Card[] newdeck = new Card [52];
        LinkedList<Card> shuffleddeck;
    	
        newdeck = Card.createSealedDeck();

        //Check for newdeck
        checkInput("1", "newdeck");
        System.out.println("Testing newdeck...");

        for(int i=0; i<=51; i++)
        {
            System.out.println("Card Number " + (i+1) + " is a " + newdeck[i].getRank() + " of " + newdeck[i].getSuit());
            Thread.sleep(150);
        }
        //End of check

        //Check for shuffleddeck
        checkInput("test", "gamedeck");
        
        shuffleddeck = Card.shuffleDeck(newdeck, 200);
        System.out.println("Testing gamedeck...");
        
        for(int i=0; i<=shuffleddeck.size()-1; i++)
        {
            System.out.println("Card Number " + (i+1) + " is a " + shuffleddeck.get(i).getRank() + " of " + shuffleddeck.get(i).getSuit());
            Thread.sleep(150);
        }
    }
}