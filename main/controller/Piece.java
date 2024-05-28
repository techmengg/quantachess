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

    public Boolean validate(int r, int c)
    {
        return true;
    }

    
}