package main.model;

public class Piece 
{
    private boolean color;
    private boolean hasMoved;
    private String pieceType;
    public Piece() 
    {
        color = true; 
        hasMoved = false; 

    }

    public Piece(boolean color)
    {
        this.color = color; // true : white and false : black
    }

    public Piece(boolean color,String type)
    {
        this.color = color; // true : white and false : black
        this.pieceType = type; 
    }


    public boolean getColor()
    {
        return color;
    }


    public String getType()
    {
        return pieceType;
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