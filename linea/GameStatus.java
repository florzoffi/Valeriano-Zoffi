package linea;

public abstract class GameStatus {
	public static final String gameEnded = "The game has ended.";
	public static final String notPlayerTurn = "It's not the player's turn.";
	public static final String NoWinnerYet = "There has been no winner yet.";
    public static final String gameEndedInDraw = "The game ended in a draw!";
	
    public abstract char isTurn();

    public abstract GameStatus playRedAt( Line game, int column );
    
    public abstract GameStatus playBlueAt( Line game, int column );

	public abstract boolean isGameFinished();
	
	public abstract char getWinner();
	
	public abstract String getEndMessage();
}