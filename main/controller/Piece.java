package main.controller;

public class Piece 
{
    private int row;
    private int col;
    private boolean color;

    public Piece() 
    {
        row = 0;
        col = 0;
        color = true;
    }

    public Piece(int r, int c, boolean turn)
    {
        row = r;
        col = c;
        color = turn; // true : white and false : black
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public boolean getColor()
    {
        return color;
    }

    public void setRow(int r)
    {
        row = r;
    }

    public void setCol(int c)
    {
        col = c;
    }

    public Boolean validate(int r, int c)
    {
        if (row == 6 && col == 4 && r == 4 && c == 4)
            return true;
        else
            return false;
    }

    
}