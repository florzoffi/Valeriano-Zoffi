package linea;

public abstract class GameStatus {
    public abstract char isTurn();

    public abstract GameStatus playRedAt(Line game, int column);
    
    public abstract GameStatus playBlueAt(Line game, int column);

	public abstract boolean isGameFinished();
}