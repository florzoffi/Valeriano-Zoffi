package linea;

public abstract class Player {
    protected char playerColor;

    public Player(char playerColor) {
        this.playerColor = playerColor;
    }

    public void playAt(Line game, int column) {
        for (int row = game.height - 1; row >= 0; row--) {
            if (game.grid[row][column] == game.c) {
                game.grid[row][column] = playerColor;
                return;
            }
        }
    }
    
    public abstract void setTurn(Line game);

	public abstract char getPlayerColor();
}