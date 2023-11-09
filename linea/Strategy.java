package linea;

public abstract class Strategy {
	
    abstract boolean matches(char strategy) ;
    
    abstract boolean checkWin(int lastColumnPlayed, char colorPlayed, Line game, GameBoard gameBoard);
    
}