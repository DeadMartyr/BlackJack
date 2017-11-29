package gameBlackJack;
 
import java.util.LinkedList;
import java.util.Scanner;

import playingCards.Card;

public class BlackJack 
{
	//Deck Reference Creation
    public static Card[] truedeck = new Card [52];
    public static LinkedList<Card> gamedeck;
    
    //Scanner Initialized
    public static Scanner userinput = new Scanner(System.in);
    
    //Hand Creation
    public static LinkedList<Card> userHand = new LinkedList<Card>();
    public static LinkedList<Card> dealerHand = new LinkedList<Card>();
	
	public static void runGame()
	{
		truedeck = Card.createSealedDeck();
        gamedeck = Card.shuffleDeck(truedeck, 200);
        
        
        userHand.clear(); //Because it remembers the cards it had from the previous trials for some reason
        dealerHand.clear(); //Because it remembers the cards it had from the previous trials for some reason
        userHand.add(Card.drawFirst(gamedeck));
        userHand.add(Card.drawFirst(gamedeck));
        dealerHand.add(Card.drawFirst(gamedeck));
        dealerHand.add(Card.drawFirst(gamedeck));
        
        
        boolean userstay = false;
        boolean dealerstay = false;
        
        
        //Actual game part
        while(true)
        {
            System.out.println();
            
            readUserHand(userHand);
            
            //All Checks
            if(checkNatural21(userHand))
            {
                System.out.println("HOLY SH*T! You got a natural 21!");
                break;
            }
            
            if(checkBust(userHand) && !checkBust(dealerHand))
            {
                System.out.println("BUST! You lose with "+ totalHand(userHand) + " against " + totalHand(dealerHand));
                break;
            }
            if(!checkBust(userHand) && checkBust(dealerHand))
            {
                System.out.println("BUST! You win with "+ totalHand(userHand) + " against " + totalHand(dealerHand));
                break;
            }
            if(checkBust(userHand) && checkBust(dealerHand))
            {
                System.out.println("DOUBLE-BUST! You tie with "+ totalHand(userHand) + " against " + totalHand(dealerHand));
                break;
            }
            
            
            //User input section
            if(!userstay)
            {
                readOpponentHand(dealerHand);
                System.out.println("Type '1' to hit, type '2' to stand");
                String input = userinput.nextLine();
                if(input.equals("1"))
                {
                    hit(userHand, gamedeck);
                }
                
                if(input.equals("2"))
                {
                    userstay = true; 
                }
            }
            
            
            
            //Idle Section
            if(userstay && !dealerstay)
            {
                System.out.println("You are waiting for the dealer to finish");
            }
            
            if(!userstay && dealerstay)
            {
                System.out.println("The dealer taps his foot impatiently. He's waiting for you to finish");
            }
            
            
            //Very Basic AI for the Dealer
            if(!dealerstay)
            {
                if(totalHand(dealerHand)<=17)
                {
                    System.out.println("The Dealer feels bold. He draws...");
                    hit(dealerHand, gamedeck);
                    
                    if(checkBust(dealerHand))
                    {
                        System.out.println("The Dealer's eyes look shifty... The dealer done f*cked up.");
                    }
                    else
                    {
                        System.out.println("The dealer has a cocky smirk on his face");
                    }
                }
                else
                {
                    System.out.println("The dealer feels confident. He stays.");
                    dealerstay = true;
                }
            }
            
            
            //Conclusion
            if(userstay && dealerstay)
            {
                System.out.println();
                System.out.println("You both agreed to stay, let's see who won...");
                
                if( ((totalHand(userHand) > totalHand(dealerHand)) && !checkBust(userHand))  ||  (checkBust(dealerHand) ))
                {
                    System.out.println("You win with " + totalHand(userHand) + " against " + totalHand(dealerHand));
                    break;
                }
                if( ((totalHand(userHand) < totalHand(dealerHand)) && !checkBust(dealerHand))  ||  (checkBust(userHand) ))
                {
                    System.out.println("You lose with " + totalHand(userHand) + " against " + totalHand(dealerHand));
                    break;
                }
                
                if((totalHand(userHand) == totalHand(dealerHand)) || (checkBust(userHand) && checkBust(dealerHand)))
                {
                    System.out.println("Draw at " + totalHand(userHand));
                    break;
                }
            }
        }
	}
	
	
	/*
    //BlackJack Methods:
    //Methods specifically meant for handling BlackJack, not just cards in general
    
    //hit(LinkedList<Card> hand, LinkedList<Card> deck)
        Draws a card from "deck" and puts it into "hand"
    //checkBust(LinkedList<Card> hand): Boolean
        Returns a boolean based on if the total value of the cards in "hand" exceed 21 or not
    //checkNatural21(LinkedList<Card> hand): Boolean
        Returns a boolean based on if "hand" has a value of 21 with only 2 cards
    //totalHand(LinkedList<Card> hand): Int
        Returns an int based on the Cards contained in "hand"
    //readUserHand(LinkedList<Card> hand)
        Outputs to the console what the user has in their hand
    //readOpponentHand(LinkedList<Card> hand)
        Outputs to the console what the user can see about the dealer's/other player's hand
    */
    public static void hit(LinkedList<Card> hand, LinkedList<Card> deck)
    {
        hand.add(Card.drawFirst(deck));
    }
    
    public static boolean checkBust(LinkedList<Card> hand)
    {
        if(totalHand(hand)>21)
        {
            return true;
        }
        
        return false;
    }
    public static boolean checkNatural21(LinkedList<Card> hand)
    {
        if(hand.size()==2 && totalHand(hand)==21)
        {
            //Second check unneeded
            //if((hand.get(1).getRank()=="Ace" || hand.get(2).getRank()=="Ace") && (hand.get(1).getValue()==10 || hand.get(2).getValue()==10))
            //{
            return true;
            //}
        }
        
        return false;
    }
    
    public static int totalHand(LinkedList<Card> hand)
    {
        int total = 0;
        int acescounted = 0;
        
        for(int i=0; i<=hand.size()-1; i++)
        {
            total = total + hand.get(i).getValue();
            
            if(hand.get(i).getRank() == "Ace")
            {
                acescounted++;
            }
        }
        
        
        //This is to check if an Ace being 11 made it go over 21, if so it removes 10 (To make the Ace count as 1) however 
        //many times it takes to either get under 21, or reach the point where all aces in the hand are already counted as 1
        for(int i=1; i<=acescounted; i++)
        {
            if(total>21)
            {
                total -= 10;
            }
        }
        
        return total;
    }
    
    public static void readUserHand(LinkedList<Card> hand)
    {
        for(int i=0; i<=hand.size()-1; i++)
        {
            System.out.println("Card Number " + (i+1) + " in your hand is a " + hand.get(i).getRank() + " of " + hand.get(i).getSuit());
        }
        System.out.println("Total: " + totalHand(userHand));
    }
    public static void readOpponentHand(LinkedList<Card> hand) 
    {
        System.out.println("Your opponent's first card is unknown.");
        for(int i=1; i<=hand.size()-1; i++)
        {
            System.out.println("Card Number " + (i+1) + " in opponent's hand is a " + hand.get(i).getRank() + " of " + hand.get(i).getSuit());
        }
    }
}