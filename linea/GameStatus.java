package linea;

public abstract class GameStatus {
    public abstract char isTurn();
    
	public abstract void setTurn();

    public abstract void playRedAt(Line game, GameBoard gameBoard, int column);
    
    public abstract void playBlueAt(Line game, GameBoard gameBoard, int column);
	
	public abstract boolean isGameFinished() ;
}