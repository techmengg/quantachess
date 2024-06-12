package main.model;

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

    public boolean validate(int r1, int c1, int r2, int c2, Piece[][] board, boolean turn)
    {
        if (chessBoard[r1][c1] == null)
            return false;

        if (chessBoard[r1][c1].getColor() != turn)
            return false;

        if (chessBoard[r1][c1].canMove(r1, c1, r2, c2, board) && !(chessBoard[r1][c1] instanceof King))
        {
            chessBoard[r2][c2] = chessBoard[r1][c1];
            chessBoard[r1][c1] = null; 
            return true;            
        }

        else
            return false;
    } 

    public void movePiece(int r1, int c1, int r2, int c2) //required for implementing castling code
    {
        if(chessBoard[r2][c2]==null)
        {
           chessBoard[r2][c2]=chessBoard[r1][c1]; 
           chessBoard[r1][c1]=null; 
        }

        if (chessBoard[r1][c1] instanceof King)
        {
            String[][] moves = squaresTheKingCanMove(r1, c1, board);
            for (int i = 0; i < moves.length; i++)
            {
                for (int j = 0; j < moves[i].length; j++)
                {
                    if (moves[i][j] != null)
                    {
                        if (r2 == Integer.parseInt(moves[i][j].substring(0, 1)) && c2 == Integer.parseInt(moves[i][j].substring(2, 3)))
                        {
                            chessBoard[r2][c2] = chessBoard[r1][c1];
                            chessBoard[r1][c1] = null; 
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    public Piece[][] getChessBoard()
    {
        return chessBoard;
    } 

    public Piece getPiece(int row, int col) 
    { 
        return chessBoard[row][col];
    }

    public int promote(int r, int c, Piece[][] board)
    {
        if (chessBoard[r][c] instanceof Pawn)
        {
            if (chessBoard[r][c].getColor())
            {
                if (r == 0)
                {
                    chessBoard[r][c] = new Queen(true);
                    return 1;
                }
            }
            else 
            {
                if (r == 7)
                {
                    chessBoard[r][c] = new Queen(false);
                    return 2;
                }
            }
        }
        return 0;
    } 
    

    public String[][] squaresTheKingCanMove(int r1, int c1, Piece[][] chessboard) 
    {
        String[][] moves = new String[3][3];

        int sr = 0;
        int er = 2;
        int sc = 0;
        int ec = 2;

        if (r1 == 7)
            er = 1;
        if (r1 == 0)
            sr = 1;
        if (c1 == 7)
            ec = 1;
        if (c1 == 0)
            sc = 1;

        for (int i = sr; i <= er; i++)
        {
            for (int j = sc; j <= ec; j++)
            {
                if (chessboard[r1][c1].canMove(r1, c1, r1 + i - 1, c1 + j - 1, chessboard) && !checkSquare(r1, c1, r1 + i - 1 , c1 + j - 1, chessboard))
                    moves[i][j] = (r1 + i - 1) + "/" + (c1 + j - 1);
            }  
        }

        // Print the moves array
        for (int i = 0; i < moves.length; i++)
        {
            for (int j = 0; j < moves[i].length; j++)
            {
                System.out.print(moves[i][j] + " ");
            }
            System.out.println();
        }

        return moves;
    }

    public boolean checkSquare(int r1, int c1, int sr, int sc, Piece[][] chessboard)
    {
        if (checkDiagonals(r1, c1, sr, sc, chessboard))
            return true;

        if (checkHorizontals(r1, c1, sr, sc, chessboard))
            return true;

        if (checkKnights(r1, c1, sr, sc, chessboard))
            return true;

        if (checkPawns(r1, c1, sr, sc, chessboard))
            return true;

        if (checkKings(r1, c1, sr, sc, chessboard))
            return true;

        // check vertical and horizontals for rooks and queens
        // check for knights

        return false;
    }

    public boolean checkDiagonals(int r1, int c1, int sr, int sc, Piece[][] chessboard)
    {
        int changerow = 1;
        int changecol = 1;
     
        for (int i = 0; i < 4; i++)
        {
            int row = sr;
            int col = sc;

            if (i == 1)  
                changerow = -1;
            if (i == 2)  
                changecol = -1;
            if (i == 3)  
                changerow = 1;

            while (row >= 0 && row <= 7 && col >= 0 && col <= 7 && chessboard[row][col] == null)
            {
                row += changerow;
                col += changecol;
            }

            if (row >= 0 && row <= 7 && col >= 0 && col <= 7)
            {
                if ((chessboard[row][col] instanceof Bishop || chessboard[row][col] instanceof Queen) && chessboard[r1][c1].getColor() != chessboard[row][col].getColor())
                    return true; 
            }

        }
        return false;
    }

    public boolean checkHorizontals(int r1, int c1, int sr, int sc, Piece[][] chessboard)
    {
        int changerow = 1;
        int changecol = 0;
     
        for (int i = 0; i < 4; i++)
        {
            int row = sr;
            int col = sc;

            if (i == 1) 
            {
                changerow = 0;
                changecol = 1; 
            }
            if (i == 2)
            {
                changerow = -1;
                changecol = 0; 
            }
            if (i == 3) 
            { 
                changerow = 0;
                changecol = -1; 
            }

            while (row >= 0 && row <= 7 && col >= 0 && col <= 7 && chessboard[row][col] == null)
            {
                row += changerow;
                col += changecol;
            }

            if (row >= 0 && row <= 7 && col >= 0 && col <= 7)
            {
                if ((chessboard[row][col] instanceof Rook || chessboard[row][col] instanceof Queen) && chessboard[r1][c1].getColor() != chessboard[row][col].getColor())
                    return true; 
            } 
            
        }
        return false;
    }

    public boolean checkKnights(int r1, int c1, int sr, int sc, Piece[][] chessboard)
    {
        int changerow = 2;
        int changecol = 1;
     
        for (int i = 0; i < 8; i++)
        {
            int row = sr;
            int col = sc;

            if (i == 1) 
            {
                changecol = -1; 
            }
            if (i == 2)
            {
                changerow = 1;
                changecol = 2; 
            }
            if (i == 3) 
            { 
                changerow = -1; 
            }

            if (i == 4) 
            {
                changerow = -2;
                changecol = 1; 
            }
            if (i == 5)
            {
                changecol = -1; 
            }
            if (i == 6) 
            { 
                changerow = 1;
                changecol = -2; 
            }
            if (i == 7) 
            { 
                changerow = -1;
            }
            
            row += changerow;
            col += changecol;

            if (row >= 0 && row <= 7 && col >= 0 && col <= 7)
            {
                if (chessboard[row][col] instanceof Knight && chessboard[r1][c1].getColor() != chessboard[row][col].getColor())
                    return true; 
            }
        }
        return false;
    }

    public boolean checkPawns(int r1, int c1, int sr, int sc, Piece[][] chessboard)
    {
        int changerow;
        int changecol = 1;

        if (chessboard[r1][c1].getColor())
            changerow = -1;
        else
            changerow = 1;
     
        for (int i = 0; i < 2; i++)
        {
            int row = sr;
            int col = sc;

            if (i == 1) 
            {
                changecol = -1; 
            }

            row += changerow;
            col += changecol;

            if (row >= 0 && row <= 7 && col >= 0 && col <= 7)
            {
                if (chessboard[row][col] instanceof Pawn && chessboard[r1][c1].getColor() != chessboard[row][col].getColor())
                    return true; 
            }
        }
        return false;
    }

    public boolean checkKings(int r1, int c1, int sr, int sc, Piece[][] chessboard)
    {
        int changerow;
        int changecol = -1;

        if (chessboard[r1][c1].getColor())
            changerow = -1;
        else
            changerow = 1;
     
        for (int i = 0; i < 5; i++)
        {
            int row = sr;
            int col = sc;

            if (i == 1) 
            {
                changecol = 0; 
            }
            if (i == 4) 
            {
                changecol = 1; 
            }

            row += changerow;
            col += changecol;

            if (row >= 0 && row <= 7 && col >= 0 && col <= 7)
            {
                if (chessboard[row][col] instanceof King && chessboard[r1][c1].getColor() != chessboard[row][col].getColor())
                    return true; 
            }
        }
        return false;
    }



}