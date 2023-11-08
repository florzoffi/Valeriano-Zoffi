package linea;

public abstract class GameStatus {
    public abstract char isTurn();
    
	public abstract void setTurn();

    public abstract GameStatus playRedAt(Line game, GameBoard gameBoard, int column);
    
    public abstract GameStatus playBlueAt(Line game, GameBoard gameBoard, int column);
	
	public abstract boolean isGameFinished() ;
}