package linea;

public class BluePlayer extends Player {
	public BluePlayer() {
		super( 'B' );
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
	public boolean isTurn(String color) {
		return "Blue" == color;
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