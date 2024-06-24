package main.model;

public class King extends Piece { 
  
    public King(boolean color , String type) {
        super(color, type); 
    }

    @Override
    public boolean canMove(int r1, int c1, int r2, int c2, Piece[][] chessboard) {
        boolean cap = false;
        if (r1 == r2 && c1 == c2) {
            return false; // Same position
        }

        if (chessboard[r2][c2] != null) {
            if (chessboard[r1][c1].getColor() == chessboard[r2][c2].getColor()) {
                return false;
            }
           cap = true;
        }
       
        // The king can move one square in any direction
        int deltaRow = Math.abs(r1 - r2);
        int deltaCol = Math.abs(c1 - c2);
        if (deltaRow <= 1 && deltaCol <= 1) {
            
            notation.moveOcc(r2,c2, chessboard[r1][c1].getType(),cap );
        }
        return deltaRow <= 1 && deltaCol <= 1;
        
        // Swap place with the rook (Castling)
    }

}