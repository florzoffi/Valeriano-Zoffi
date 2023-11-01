package linea;

public class GameStatus {
    private final Line game;

    public GameStatus(Line game) {
        this.game = game;
    }

    public boolean checkVerticalWin() {
        // Check for a vertical win
        for (int x = 0; x < game.base; x++) {
            for (int y = 0; y <= game.height - 4; y++) {
                char chip = game.grid.get(y).get(x).getValue();
                if (chip != game.c && game.grid.get(y + 1).get(x).getValue() == chip &&
                        game.grid.get(y + 2).get(x).getValue() == chip && game.grid.get(y + 3).get(x).getValue() == chip) {
                    return true; // Vertical win detected
                }
            }
        }
        return false;
    }

    public boolean checkDiagonalWinBottomLeftToTopRight() {
        // Check for a diagonal win (bottom-left to top-right)
        for (int x = 0; x <= game.base - 4; x++) {
            for (int y = 0; y <= game.height - 4; y++) {
                char chip = game.grid.get(y).get(x).getValue();
                if (chip != game.c && game.grid.get(y + 1).get(x + 1).getValue() == chip &&
                        game.grid.get(y + 2).get(x + 2).getValue() == chip && game.grid.get(y + 3).get(x + 3).getValue() == chip) {
                    return true; // Diagonal win detected (bottom-left to top-right)
                }
            }
        }
        return false;
    }

    public boolean checkDiagonalWinTopLeftToBottomRight() {
        // Check for a diagonal win (top-left to bottom-right)
        for (int x = 0; x <= game.base - 4; x++) {
            for (int y = 3; y < game.height; y++) {
                char chip = game.grid.get(y).get(x).getValue();
                if (chip != game.c && game.grid.get(y - 1).get(x + 1).getValue() == chip &&
                        game.grid.get(y - 2).get(x + 2).getValue() == chip && game.grid.get(y - 3).get(x + 3).getValue() == chip) {
                    return true; // Diagonal win detected (top-left to bottom-right)
                }
            }
        }
        return false;
    }

    public boolean checkHorizontalWin() {
        // Check for a horizontal win
        for (int y = 0; y < game.height; y++) {
            for (int x = 0; x <= game.base - 4; x++) {
                char chip = game.grid.get(y).get(x).getValue();
                if (chip != game.c && game.grid.get(y).get(x + 1).getValue() == chip &&
                        game.grid.get(y).get(x + 2).getValue() == chip && game.grid.get(y).get(x + 3).getValue() == chip) {
                    return true; // Horizontal win detected
                }
            }
        }
        return false;
    }

    public boolean checkDraw() {
        for (int y = 0; y < game.height; y++) {
            for (int x = 0; x <= game.base - 4; x++) {
                boolean isDraw = true;
                for (int i = 0; i < 4; i++) {
                    if (game.grid.get(y).get(x + i).getValue() == game.c) {
                        isDraw = false; // This sequence has an empty cell, move to the next sequence
                        break;
                    }
                }
                if (isDraw) {
                    return true; // The game is a draw
                }
            }
        }
        return false; 
    }

    public boolean isGameFinished() {
        return checkVerticalWin() || checkDiagonalWinBottomLeftToTopRight() ||
                checkDiagonalWinTopLeftToBottomRight() || checkHorizontalWin() || checkDraw();
    }
}