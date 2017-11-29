package playingCards;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Random;

public class Card
{
    // Instance variables 
    public Suit suit;
    public Rank rank;

    // Randomizer Initialized
    public static Random randomizer = new Random();

    //Card Constructor
    public Card(Suit x, Rank y)
    {
        // Initializing instance variables
        suit = x;
        rank = y;
    }

    /*
    //Information Recall Methods:
    //Used to recall information about each individual card

    //getSuit():
        Returns a String saying the Suit based on the Suit enum("Spades", "Clubs", "Diamonds", "Hearts"
    //getRank():
        Returns a String saying the Rank based on the Rank enum("Ace", "Two", "Three" ... "Jack", "Queen", "King")
    //getValue():
        Returns an int based on the Rank enum to be used when totaling a player's hand
    */
    public String getSuit()
    {
        switch(suit)
        {
            case SPADES:
                return "Spades";  
            case CLUBS:
                return "Clubs";
            case DIAMONDS:
                return "Diamonds";
            case HEARTS:
                return "Hearts";

            default:
                return "Unknown Suit";
        }
    }

    public String getRank()
    {
        switch(rank) 
        {
            case ACE:
                return "Ace";
            case TWO:
                return "Two";
            case THREE:
                return "Three";
            case FOUR:
                return "Four";
            case FIVE:
                return "Five";
            case SIX:
                return "Six";
            case SEVEN:
                return "Seven";
            case EIGHT:
                return "Eight";
            case NINE:
                return "Nine";
            case TEN:
                return "Ten";
            case JACK:
                return "Jack";
            case QUEEN:
                return "Queen";
            case KING:
                return "King";

            default:
                return "Unknown Rank";
        }
    }

    public int getValue()
    {
        switch(rank) 
        {
            case ACE:
                return 11; //When totaling the value in "totalHand" in deducts 10 from this 
                           //value if it's over 21 for each Ace until it's under 21
                
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
                return 10;
            case JACK:
                return 10;
            case QUEEN:
                return 10;
            case KING:
                return 10;

            default:
                return 0;
        }
    }

    
    
    /*
    //Deck Methods:
    //Methods used for Deck construction and manipulation
    
    //createdSealedDeck(): Card[]
        Returns an array of Cards that represents buying a brand-new deck of cards at the store
        CANNOT be used as an array though, needs to be converted into a List
    //shuffleDeck(Card[] deck, int swaps): LinkedList
        Shuffles the base deck (which is an array) "swaps" amount of times 
        and returns a playable deck (which is a List).
        Simulates opening a brand-new deck of cards and shuffling it for the first time basically, 
        my logic is: "You have to unseal it to be able to use it"
    //drawFirst(List<Card> deck): Card
        Returns the first Card from a deck and removes it
    //drawLast(List<Card> deck): Card
        Returns the last Card from a deck and removes it
    //drawMid(List<Card> deck, i): Card
        Returns a card a position "i" from a deck and removes it
    */
    public static Card[] createSealedDeck()
    {
        Card[] sealeddeck = new Card [52];
        //Temporary values used for Conversion
        Suit tempSuit;
        Rank tempRank;

        //Uses two loops to Construct the deck as opposed to just individually assigning each card value
        //Contains 52 cards, numbered 0-51 (1 is added during playback so it's read 1-52)
        for(int s=1, cardpos=0; s<=4; s++) 
        {
            //Conversion from ints to Card Info
            switch(s)
            {
                case 1:
                tempSuit = Suit.SPADES;
                break;
                case 2:
                tempSuit = Suit.CLUBS;
                break;
                case 3:
                tempSuit = Suit.DIAMONDS;
                break;
                case 4:
                tempSuit = Suit.HEARTS;
                break;

                default:
                tempSuit = Suit.SPADES;
                break;
            }

            for(int r=1; r<=13; r++)
            {
                //Conversion from ints to Card Info
                switch(r)
                {
                    case 1:
                    tempRank = Rank.ACE;
                    break;
                    case 2:
                    tempRank = Rank.TWO;
                    break;
                    case 3:
                    tempRank = Rank.THREE;
                    break;
                    case 4:
                    tempRank = Rank.FOUR;
                    break;
                    case 5:
                    tempRank = Rank.FIVE;
                    break;
                    case 6:
                    tempRank = Rank.SIX;
                    break;
                    case 7:
                    tempRank = Rank.SEVEN;
                    break;
                    case 8:
                    tempRank = Rank.EIGHT;
                    break;
                    case 9:
                    tempRank = Rank.NINE;
                    break;
                    case 10:
                    tempRank = Rank.TEN;
                    break;
                    case 11:
                    tempRank = Rank.JACK;
                    break;
                    case 12:
                    tempRank = Rank.QUEEN;
                    break;
                    case 13:
                    tempRank = Rank.KING;
                    break;

                    default:
                    tempRank = Rank.ACE;
                    break;
                }

                sealeddeck[cardpos] = new Card(tempSuit,tempRank);
                cardpos++;
            }
        }

        return sealeddeck;
    }

    public static LinkedList<Card> shuffleDeck(Card[] deck, int swaps)
    {
        LinkedList<Card> shuffleddeck = new LinkedList<Card>(Arrays.asList(deck));
        int size = shuffleddeck.size();

        Card tempcard;
        int cardx;
        int cardy;

        for(int i=0; i<=swaps; i++)
        {
            cardx = randomizer.nextInt(size);
            cardy = randomizer.nextInt(size);

            tempcard = shuffleddeck.get(cardx);

            shuffleddeck.set(cardx, shuffleddeck.get(cardy));
            shuffleddeck.set(cardy, tempcard);
        }

        return shuffleddeck;
    }

    public static Card drawFirst(LinkedList<Card> deck)
    {
        Card drawn;
        drawn = deck.get(0);

        deck.remove(0);

        return drawn;
    }
    public static Card drawLast(LinkedList<Card> deck)
    {
        Card drawn;
        drawn = deck.get(deck.size()-1);

        deck.remove(deck.size()-1);

        return drawn;
    }
    public static Card drawMid(LinkedList<Card> deck, int i)
    {
        Card drawn;
        drawn = deck.get(i);

        deck.remove(i);

        return drawn;
    }

}