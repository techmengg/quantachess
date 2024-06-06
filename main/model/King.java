package main.model;

public class King extends Piece {
    public King(boolean color) {
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

        // The king can move one square in any direction
        int deltaRow = Math.abs(r1 - r2);
        int deltaCol = Math.abs(c1 - c2);

        return deltaRow <= 1 && deltaCol <= 1;

        // Swap place with the rook (Castling)
        

    }
}