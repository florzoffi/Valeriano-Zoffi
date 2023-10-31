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
}