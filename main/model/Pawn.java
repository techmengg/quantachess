package main.model;

public class Pawn extends Piece
{
    public Pawn(boolean color,String type)
    {
        super(color,type);     
    }

    public boolean canMove(int r1, int c1, int r2, int c2, Piece[][] chessboard)
    {
        if (r1 == r2 && c1 == c2)
            return false;

        if (super.getColor()) 
        {
            if (chessboard[r2][c2] != null)
            {
                if (r2 == r1 - 1 && c1 == c2 + 1 && !chessboard[r2][c2].getColor()) {
                    setHasMoved(true);
                    notation.moveOcc(r2,c2, chessboard[r1][c1].getType(),true );


                    return true;
                }
                else if (r2 == r1 - 1 && c1 == c2 - 1 && !chessboard[r2][c2].getColor()){
                    setHasMoved(true);
                    notation.moveOcc(r2,c2, chessboard[r1][c1].getType(),true );
                    return true;
                }
            }     
            if (!getHasMoved() && r2 == r1 - 2 && c1 == c2 && chessboard[r2][c2] == null) {
                setHasMoved(true);
                notation.moveOcc(r2,c2, chessboard[r1][c1].getType(),false );
                return true;
            }
            else if (r2 == r1 - 1 && c1 == c2 && chessboard[r2][c2] == null) {
                setHasMoved(true);
                notation.moveOcc(r2,c2, chessboard[r1][c1].getType(),false );
                return true;
            }
                
            else 
                return false;
        }
        else 
        {
            if (chessboard[r2][c2] != null)
            {
                if (r2 == r1 + 1 && c1 == c2 + 1 && chessboard[r2][c2].getColor()) {
                    setHasMoved(true);
                    notation.moveOcc(r2,c2, chessboard[r1][c1].getType(),true );
                    return true;
                }
                else if (r2 == r1 + 1 && c1 == c2 - 1 && chessboard[r2][c2].getColor()) {
                    setHasMoved(true);
                    notation.moveOcc(r2,c2, chessboard[r1][c1].getType(),true );
                    return true;
                }
            }
            if (!getHasMoved() && r2 == r1 + 2 && c1 == c2 && chessboard[r2][c2] == null) {
                setHasMoved(true);
                notation.moveOcc(r2,c2, chessboard[r1][c1].getType(),false );
                return true;
            }
            else if (r2 == r1 + 1 && c1 == c2 && chessboard[r2][c2] == null) {
                setHasMoved(true);
                notation.moveOcc(r2,c2, chessboard[r1][c1].getType(),false );
                return true;
            }
            else 
                return false;
        }
    }
}