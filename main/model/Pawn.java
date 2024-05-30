package main.model;

public class Pawn extends Piece
{
    private boolean hasMoved;

    public Pawn(boolean color)
    {
        super(color);     
        hasMoved = false; 
    }

    public boolean canMove(int r1, int c1, int r2, int c2, Piece[][] chessboard)
    {
        if (!(r1 == r2 && c1 == c2))
        {

            if (super.getColor()) 
            {
                if (chessboard[r2][c2] != null)
                {
                    if (r2 == r1 - 1 && c1 == c2 + 1 && !chessboard[r2][c2].getColor())
                        return true;
                    else if (r2 == r1 - 1 && c1 == c2 - 1 && !chessboard[r2][c2].getColor())
                        return true;
                }
                if (!hasMoved && r2 == r1 - 2 && c1 == c2 && chessboard[r2][c2] == null)
                    return true;
                else if (r2 == r1 - 1 && c1 == c2 && chessboard[r2][c2] == null)
                    return true;
                    
                else 
                    return false;
            }
            else 
            {
                if (chessboard[r2][c2] != null)
                {
                    if (r2 == r1 + 1 && c1 == c2 + 1 && chessboard[r2][c2].getColor())
                        return true;
                    else if (r2 == r1 + 1 && c1 == c2 - 1 && chessboard[r2][c2].getColor())
                        return true;
                }
                if (!hasMoved && r2 == r1 + 2 && c1 == c2 && chessboard[r2][c2] == null)
                    return true;
                else if (r2 == r1 + 1 && c1 == c2 && chessboard[r2][c2] == null)
                    return true;
                
                else 
                    return false;
            }
        }
        else 
            return false;
    }
}