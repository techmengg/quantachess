package main.model;

public class Bishop extends Piece
{
    public Bishop(boolean color , String type)
    {
        super(color, type);
    }


    @Override
    public boolean canMove(int r1, int c1, int r2, int c2, Piece[][] chessboard) {
        if (r1 == r2 && c1 == c2) {
            return false; // Same position
        }

        if (chessboard[r2][c2] != null && isDiagonalClear(r1, c1, r2, c2, chessboard) == true) {
            if (chessboard[r1][c1].getColor() == chessboard[r2][c2].getColor()) {
                return false;

            }
            else {
                notation.moveOcc(r2,c2, chessboard[r1][c1].getType(),true );
                return true;

            }

                }
           // System.out.println(false);

        if (isDiagonalClear(r1, c1, r2, c2, chessboard) == true) {
            notation.moveOcc(r2,c2, chessboard[r1][c1].getType(),false );
            return true;
        }
        else {
            return false;
        }
         
         
    }

    private boolean isDiagonalClear(int r1, int c1, int r2, int c2, Piece[][] chessboard) {
        int dr = Integer.signum(r2 - r1);
        int dc = Integer.signum(c2 - c1);
        int r = r1 + dr;
        int c = c1 + dc;
        while (r != r2 || c != c2) {
            if (chessboard[r][c] != null) {
                return false; // Path is blocked
            }
            r += dr;
            c += dc;
        }
        return true; // Path is clear
    }
}