package main.controller;

public class Board
{
    private Piece[][] chessBoard;

    public Board()
    {
        chessBoard = new Piece[8][8];

        // black pieces
        chessBoard[0][0] = new Rook(false);
        chessBoard[0][1] = new Knight(false);
        chessBoard[0][2] = new Bishop(false);
        chessBoard[0][3] = new Queen(false);
        chessBoard[0][4] = new King(false);
        chessBoard[0][5] = new Bishop(false);
        chessBoard[0][6] = new Knight(false);
        chessBoard[0][7] = new Rook(false);
        chessBoard[1][0] = new Pawn(false);
        chessBoard[1][1] = new Pawn(false);
        chessBoard[1][2] = new Pawn(false);
        chessBoard[1][3] = new Pawn(false);
        chessBoard[1][4] = new Pawn(false);
        chessBoard[1][5] = new Pawn(false);
        chessBoard[1][6] = new Pawn(false);
        chessBoard[1][7] = new Pawn(false);

        // white pieces
        chessBoard[7][0] = new Rook(true);
        chessBoard[7][1] = new Knight(true);
        chessBoard[7][2] = new Bishop(true);
        chessBoard[7][3] = new Queen(true);
        chessBoard[7][4] = new King(true);
        chessBoard[7][5] = new Bishop(true);
        chessBoard[7][6] = new Knight(true);
        chessBoard[7][7] = new Rook(true);
        chessBoard[6][0] = new Pawn(true);
        chessBoard[6][1] = new Pawn(true);
        chessBoard[6][2] = new Pawn(true);
        chessBoard[6][3] = new Pawn(true);
        chessBoard[6][4] = new Pawn(true);
        chessBoard[6][5] = new Pawn(true);
        chessBoard[6][6] = new Pawn(true);
        chessBoard[6][7] = new Pawn(true);     
    }

    public boolean validate(int r1, int c1, int r2, int c2)
    {
        return chessBoard[r1][c1].canMove(r1, c2, r2, c2);
    }


}