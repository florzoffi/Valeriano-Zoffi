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
	public boolean isTurn(String color) {
		return "Red" == color;
	}

	@Override
	public void playAt(Line game, int column) {
		int y = game.height - 1;
		
		if (column < 0 || column >= game.base) {
		    setTurn(game);
		    throw new RuntimeException("Invalid move. Your turn has been lost");
		}
		
		while (y >= 0 && !game.grid.get(y).get(column).isEmpty()) {
		    y--;
		    if (y < 0) {
		        setTurn(game);
		        throw new RuntimeException("Invalid move. Your turn has been lost");
		    }
		}

	    game.grid.get(y).get(column).setValue(playerColor);
	    setTurn(game);
	}
}