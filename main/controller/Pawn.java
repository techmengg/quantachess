package main.controller;

public class Pawn extends Piece
{
    private boolean hasMoved;

    public Pawn(boolean color)
    {
        super(color);     
        hasMoved = false; 
    }

}