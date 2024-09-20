import java.util.ArrayList;
import java.util.Scanner;
//import java.util.ArrayList;
public class Trash {

	public static String cardInHand;
	public static Deck deck;
	private static Scanner input;
	private static int players;
	private static ArrayList<Player> playerList = new ArrayList<Player>();
	public static void main(String[] args) 
	{
		while(true)
		{
			System.out.println("Press Enter to begin.");
			input = new Scanner(System.in);
			deck = new Deck(1);
			System.out.println("How many players?");
			players = input.nextInt();
			input.nextLine();
			for(int i = 0; i < players; i++)
			{
				System.out.println("What is the name of player " + (i+1));
				String name = input.nextLine();
				Player newer = new Player(name);
				playerList.add(newer);
			}
			while(playerList.size() > 1)
			{
				for(int i = 0; i < playerList.size(); i++)
				{
					System.out.println(playerList.get(i).getName() + "'s turn!");
					if(doPlayerTurn(playerList.get(i)))
					{
						playerList.get(i).win();
						if(playerList.get(i).getBoardSize() == 0)
						{
							playerList.remove(i);
						}
					}
				}
			}
		}
		input.close();
	}
	public static boolean doPlayerTurn(Player active)
	{
		int cardsRemaining = active.getBoardSize();
		while(true)
		{
			if(cardsRemaining == 0)
			{
				return true;
			}
			System.out.println("Your board: ");
			active.printBoard();
			if(deck.getDiscardSize() == 0 && cardInHand == null)
			{
				cardInHand = deck.dealCard();
			}
			else if(cardInHand == null)
			{
				System.out.println("Would you like to pick up the discarded card (" + deck.getTopDiscard() + ")?");
				System.out.println("If no, you will draw from the deck. (y/n)");
				if(input.nextLine().equals("y"))
				{
					cardInHand = deck.getTopDiscard();
				}
				else
				{
					cardInHand = deck.dealCard();
				}
			}
			System.out.println("You are holding " + cardInHand);
			if(cardInHand.charAt(0) == 'Q')
			{
				System.out.println("Picking up a Queen ends your turn. Press enter.");
				@SuppressWarnings("unused")
				String what = input.nextLine();
				deck.discard(cardInHand);
				cardInHand = null;
				for(String val : active.getBoard())
				{
					if(val.equals("X"))
					{
						return false;
					}
				}
				return true;
			}
			else if(cardInHand.charAt(0) == 'J')
			{
				System.out.println("Picking up a Jack allows you to draw a new card from the deck. Press Enter.");
				deck.discard(cardInHand);
				cardInHand = deck.dealCard();
				@SuppressWarnings("unused")
				String what = input.nextLine();
				
			}
			else if(cardInHand.charAt(0) == 'K')
			{
				System.out.println("Kings are wild!");
				System.out.println();
				System.out.println("Which number place would you like to place it in? (enter 'discard' to discard it)");
				String resp = input.nextLine();
				if(resp.equals("discard"))
				{
					deck.discard(cardInHand);
					cardInHand = null;
					for(String val : active.getBoard())
					{
						if(val.equals("X"))
						{
							return false;
						}
					}
					return true;
				}
				if(resp.equals("A"))
				{
					cardInHand = active.playCard(0, cardInHand);
				}
				else if(resp.equals("10"))
				{
					cardInHand = active.playCard(9, cardInHand);
				}
				else
				{
					cardInHand = active.playCard(Character.getNumericValue(resp.charAt(0))-1, cardInHand);
					input.nextLine();
				}
			}
			else if(cardInHand.substring(0, 2).equals("10"))
			{
				System.out.println("Would you like to place it in your 10 spot?");
				if(input.nextLine().equals("n"))
				{
					deck.discard(cardInHand);
					cardInHand = null;
					for(String val : active.getBoard())
					{
						if(val.equals("X"))
						{
							return false;
						}
					}
					return true;
				}
				cardInHand = active.playCard(9, cardInHand);
			}
			else if(cardInHand.charAt(0) == 'A')
			{
				System.out.println("Would you like to place it in your A spot?");
				if(input.nextLine().equals("n"))
				{
					deck.discard(cardInHand);
					cardInHand = null;
					for(String val : active.getBoard())
					{
						if(val.equals("X"))
						{
							return false;
						}
					}
					return true;
				}
				cardInHand = active.playCard(0, cardInHand);
			}
			else if(Character.getNumericValue(cardInHand.charAt(0)) > active.getBoardSize())
			{
				System.out.println("Picking up a card you cannot place ends your turn. Press enter.");
				@SuppressWarnings("unused")
				String what = input.nextLine();
				deck.discard(cardInHand);
				cardInHand = null;
				for(String val : active.getBoard())
				{
					if(val.equals("X"))
					{
						return false;
					}
				}
				return true;
			}
			else
			{
				System.out.println("Would you like to place it in your " + cardInHand.charAt(0) + " spot?");
				if(input.nextLine().equals("n"))
				{
					deck.discard(cardInHand);
					cardInHand = null;
					for(String val : active.getBoard())
					{
						if(val.equals("X"))
						{
							return false;
						}
					}
					return true;
				}
				cardInHand = active.playCard(Character.getNumericValue(cardInHand.charAt(0))-1, cardInHand);
			}
		}
	}

}
