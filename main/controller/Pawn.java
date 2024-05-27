package main.controller;

public class Piece 
{
    private int row;
    private int col;

    public Piece(int r, int c)
    {
        row = r;
        col = c;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
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