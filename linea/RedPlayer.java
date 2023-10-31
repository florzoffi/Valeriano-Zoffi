package linea;

public class RedPlayer extends Player {
	public RedPlayer() {
		super('R');
	}
	
	@Override
    public void setTurn(Line game) {
		game.turn = 'B';
        game.player = new BluePlayer(); // Implement RedPlayer's setTurn logic here
    }

	@Override
	public char getPlayerColor() {
		return 'R';
	}
}