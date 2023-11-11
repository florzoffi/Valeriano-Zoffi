package linea;

public class DrawStatus extends GameStatus {

	public DrawStatus( GameBoard gameBoard ) {
        super();
    }

    @Override
    public char isTurn() {
        return ' ';
    }

    @Override
    public GameStatus playRedAt( Line game, int column ) {
        throw new RuntimeException( gameEnded );
    }

    @Override
    public GameStatus playBlueAt( Line game, int column ) {
        throw new RuntimeException( gameEnded );
    }

    @Override
    public boolean isGameFinished() {
        return true;
    }
    
    @Override
    public String getEndMessage() {
        return gameEndedInDraw ;
    }

	@Override
	public char getWinner() {
		throw new RuntimeException( gameEndedInDraw );
	}
}