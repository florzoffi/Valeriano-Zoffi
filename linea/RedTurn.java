package linea;

public class RedTurn extends TurnHandler {
    public RedTurn() {
        super('R');
    }

    @Override
    public void setTurn(Line game) {
        game.turn = 'B';
        game.player = new BlueTurn();
    }

    @Override
    public char getPlayerColor() {
        return 'R';
    }

    @Override
    public boolean isTurn(String color) {
        return "Red".equals(color);
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