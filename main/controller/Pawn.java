package main.controller;

public class Pawn extends Piece
{
    private boolean hasMoved;

    public Pawn(boolean color)
    {
        super(color);     
        hasMoved = false; 
    }

    public boolean canMove(int r1, int c1, int r2, int c2)
    {
        if (super.getColor()) 
        {
            if (!hasMoved && r2 == r1 - 2 && c1 == c2)
                return true;
            else if (r2 == r1 - 1 && c1 == c2)
                return true;
            else 
                return false;
        }
        else 
        {
            if (!hasMoved && r2 == r1 + 2 && c1 == c2)
                return true;
            else if (r2 == r1 + 1 && c1 == c2)
                return true;
            else 
                return false;
        }
    }


    

}