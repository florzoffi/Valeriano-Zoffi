package linea;

public abstract class TurnHandler {
    protected char playerColor;

    public TurnHandler(char playerColor) {
        this.playerColor = playerColor;
    }

    public abstract void playAt(Line game, GameBoard gameBoard, int column);
    
    public abstract void setTurn(Line game);

	public abstract char getPlayerColor();

	public abstract boolean isTurn(String color);
}