package main.controller;

public class Piece 
{
    private boolean color;

    public Piece() 
    {
        color = true;
    }

    public Piece(boolean color)
    {
        this.color = color; // true : white and false : black
    }

    public boolean getColor()
    {
        return color;
    }

    public Boolean canMove(int r1, int c1, int r2, int c2)
    {
        return true;
    }

    
}