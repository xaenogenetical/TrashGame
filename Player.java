public class Player {
	private int boardSize;
	private String name;
	private String[] board, realBoard;
	public Player(String name) {
		this.name = name;
		boardSize = 10;
		board = new String[boardSize];
		realBoard = new String[boardSize];
		this.initialize();
	}
	public void win()
	{
		boardSize--;
		board = new String[boardSize];
		initialize();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void initialize()
	{
		for(int i = 0; i < board.length; i++)
		{
			board[i] = "X";
			realBoard[i] = Trash.deck.dealCard();
		}
	}
	public int getBoardSize() {
		return boardSize;
	}
	public void printBoard() {
		for(int i = 0; i < 5 && i < board.length; i++)
		{
			System.out.print(board[i] + " ");
		}
		System.out.println();
		for(int i = 5; i < board.length; i++)
		{
			System.out.print(board[i] + " ");
		}
		System.out.println();
	}
	public String playCard(int place, String cardInHand) 
	{
		board[place] = cardInHand;
		String returner = realBoard[place];
		realBoard[place] = cardInHand;
		return returner;
	}
	public String[] getBoard()
	{
		return board;
	}
}
