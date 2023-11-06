package linea;

public class BlueTurn extends TurnHandler {
	public BlueTurn() {
		super( 'B' );
	}
	
	@Override
    public void setTurn(Line game) {
    	game.turn = 'R';
        game.player = new RedTurn();
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
    public void playAt(Line game, GameBoard gameBoard, int column) {

        if (column < 0 || column >= gameBoard.getNumColumns() || gameBoard.isColumnFull(column) ) {
            setTurn(game);
            throw new RuntimeException("Invalid move. Your turn has been lost");
        }

        gameBoard.placeChip(column, playerColor);
        setTurn(game);
    }
}