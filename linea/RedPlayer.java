package linea;

public class RedPlayer extends Player {
	public RedPlayer() {
		super('R');
	}
	
	@Override
    public void setTurn(Line game) {
		game.turn = 'B';
        game.player = new BluePlayer();
    }

	@Override
	public char getPlayerColor() {
		return 'R';
	}

	@Override
	public boolean isTurn(String p) {
		return "Red" == p;
	}

	@Override
	public void playAt(Line game, int column) {
		if (column < 0 || column >= game.base || game.isColumnFull(column)) {
        	setTurn(game);
            throw new RuntimeException("Invalid move. Your turn has been lost");
        }
		
		for (int row = game.height - 1; row >= 0; row--) {
            if (game.grid[row][column] == game.c) {
                game.grid[row][column] = playerColor;
                return;
            }
        }
	}
}