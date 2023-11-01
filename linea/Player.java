package linea;

public abstract class Player {
    protected char playerColor;

    public Player(char playerColor) {
        this.playerColor = playerColor;
    }

    public abstract void playAt(Line game, int column);
    
    public abstract void setTurn(Line game);

	public abstract char getPlayerColor();

	public abstract boolean isTurn(String color);
}