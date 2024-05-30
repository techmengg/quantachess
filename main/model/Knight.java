package main.model;

public class Knight extends Piece {
    public Knight(boolean color) {
        super(color);
    }

    @Override
    public boolean canMove(int r1, int c1, int r2, int c2, Piece[][] chessboard) {
        if (r1 == r2 && c1 == c2) {
            return false; // Same position
        }

        if (chessboard[r2][c2] != null) {
            if (chessboard[r1][c1].getColor() == chessboard[r2][c2].getColor()) {
                return false;
            }
        }

        // Knight moves in an L-shape
        int deltaRow = Math.abs(r1 - r2);
        int deltaCol = Math.abs(c1 - c2);

        return (deltaRow == 2 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 2);
    }
}
