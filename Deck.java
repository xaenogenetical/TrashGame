import java.util.ArrayList;
import java.lang.Math;

public class Deck {
	private static final String[] suits = new String[] {"♣", "♥", "♠", "♦"};
	private static final String[] ranks = new String[] {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private ArrayList<String> deckOrder = new ArrayList<String>();
	private ArrayList<String> discard = new ArrayList<String>();
	public Deck(int numOfDecks) {
		for(int i = 0; i < numOfDecks; i++)
		{
			for(String suit : suits)
			{
				for(String card : ranks)
				{
					deckOrder.add(card + suit);
				}
			}
		}
		this.shuffle();
	}
	
	public String dealCard()
	{
		if(deckOrder.size() == 0)
		{
			System.out.println("Deck is empty, shuffling discard into a new deck.");
			for(String card : discard)
			{
				deckOrder.add(card);
			}
		}
		return deckOrder.remove(deckOrder.size()-1);
	}
	public String grabDiscard()
	{
		return(discard.remove(discard.size()-1));
	}
	public void discard(String discarded)
	{
		discard.add(discarded);
	}
	public int getDiscardSize() 
	{
		return discard.size();
	}
	public String getTopDiscard()
	{
		return discard.get(discard.size()-1);
	}
	public void reshuffle()
	{
		for(String card : discard)
		{
			deckOrder.add(card);
		}
		this.shuffle();
	}
	public void shuffle()
	{
		ArrayList<String> tempCopy = new ArrayList<String>();
		for(String card : deckOrder)
		{
			tempCopy.add(card);
		}
		deckOrder = new ArrayList<String>();
		while(tempCopy.size() > 0)
		{
			deckOrder.add(tempCopy.remove((int)(Math.random()*tempCopy.size())));
		}
	}
	
}
