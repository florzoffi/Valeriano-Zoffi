package linea;

public class BluePlayer extends Player {
	public BluePlayer() {
		super('B');
	}
	
	@Override
    public void setTurn(Line game) {
    	game.turn = 'R';
        game.player = new RedPlayer();
    }

	@Override
	public char getPlayerColor() {
		return 'B';
	}

	@Override
	public boolean isTurn(String p) {
		return "Blue" == p;
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