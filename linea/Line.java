package linea;

import java.util.ArrayList;
import java.util.List;

public class Line {
	public char turn;
	public int base;
	public int height;
	public char c;
	private GameBoard gameBoard;
	public TurnHandler player;
	public GameStatus gameStatus;

	public Line (int base, int height, char c) {
		this.base = base;
		this.height = height;
		this.c = c;
		
		gameBoard = new GameBoard(base, height);
        gameStatus = new OnGoingStatus(gameBoard);
	    player = new RedTurn();
		turn = 'R';
	}

	public boolean finished() {
	    return gameStatus.isGameFinished();
	}
	
	public Line play(String color, int column) {
		if (!player.isTurn(color)) {
			throw new RuntimeException( "It's not the player's turn." );
		}
		
		player.playAt(this, gameBoard, column);
        return this;
	}
	
	
	public char[] show() {
	    char[] displayBoard = new char[(base + 3) * (height + 2)];

	    for (int col = 0; col < base + 2; col++) {
	        for (int row = 0; row < height + 2; row++) {
	            if (col == 0 || col == base + 1) {
	                // Bordes verticales de las columnas
	                displayBoard[row * (base + 3) + col] = '|';
	            } else if (row == 0 || row == height + 1) {
	                // Borde horizontal superior e inferior
	                displayBoard[row * (base + 3) + col] = '-';
	            } else {
	                // Celdas del juego
	                char cellValue = gameBoard.grid.get(col - 1).get(row - 1).getPlayerColor();
	                displayBoard[row * (base + 3) + col] = cellValue;
	            }

	            if (col == base + 1) {
	                // Salto de línea después de la última columna
	                displayBoard[row * (base + 3) + col + 1] = '\n';
	            }
	        }
	    }

	    return displayBoard;
	}
	
	public Line playBlueAt(int column) {
	    if (gameStatus.isGameFinished() || !player.isTurn("Blue")) {
	        throw new RuntimeException("It's not the Blue player's turn.");
	    }

	    if (column < 0 || column >= gameBoard.getNumColumns() || gameBoard.isColumnFull(column)) {
	        throw new RuntimeException("Invalid move. Your turn has been lost");
	    }

	    gameBoard.placeChip(column, 'B');
	    player = new RedTurn(); // Cambiamos el turno al jugador rojo
	    return this;
	}


	public Line playRedAt(int column) {
	    if (gameStatus.isGameFinished() || !player.isTurn("Red")) {
	        throw new RuntimeException("It's not the Red player's turn.");
	    }

	    if (column < 0 || column >= gameBoard.getNumColumns() || gameBoard.isColumnFull(column)) {
	        throw new RuntimeException("Invalid move. Your turn has been lost");
	    }

	    gameBoard.placeChip(column, 'R');
	    player = new BlueTurn(); // Cambiamos el turno al jugador azul
	    return this;
	}
	
	public boolean redTurn() {
		return turn == 'R';
	}
	
	public boolean blueTurn() {
		return turn == 'B';
	}

}
