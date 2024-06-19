package main.model;

public class Piece 
{
    private boolean color;
    private boolean hasMoved;
    
    public Piece() 
    {
        color = true; 
        hasMoved = false; 
    }

    public Piece(boolean color)
    {
        this.color = color; // true : white and false : black
    }

    public boolean getColor()
    {
        return color;
    }

    public boolean canMove(int r1, int c1, int r2, int c2, Piece[][] chessboard)
    {
        return false;
    }  

    public boolean getHasMoved()
    {
        return hasMoved; 
    }

    public void setHasMoved(boolean value)
    {
        hasMoved = value;
    }



    
}