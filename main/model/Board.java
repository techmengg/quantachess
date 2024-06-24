package main.model;

public class Board
{
    private Piece[][] chessBoard;
    private String whiteKing;
    private String blackKing;
    private boolean check;
    private boolean resign;

    public Board()
    {
        chessBoard = new Piece[8][8];

        // black pieces
        chessBoard[0][0] = new Rook(false,"R");
        chessBoard[0][1] = new Knight(false,"K");
        chessBoard[0][2] = new Bishop(false,"B");
        chessBoard[0][3] = new Queen(false,"Q");
        chessBoard[0][4] = new King(false,"K");
        chessBoard[0][5] = new Bishop(false,"B");
        chessBoard[0][6] = new Knight(false,"K");
        chessBoard[0][7] = new Rook(false,"R");
        chessBoard[1][0] = new Pawn(false,"P");
        chessBoard[1][1] = new Pawn(false,"P");
        chessBoard[1][2] = new Pawn(false,"P");
        chessBoard[1][3] = new Pawn(false,"P");
        chessBoard[1][4] = new Pawn(false,"P");
        chessBoard[1][5] = new Pawn(false,"P");
        chessBoard[1][6] = new Pawn(false,"P");
        chessBoard[1][7] = new Pawn(false,"P");

        // white pieces
        chessBoard[7][0] = new Rook(true,"R");
        chessBoard[7][1] = new Knight(true,"K");
        chessBoard[7][2] = new Bishop(true,"B");
        chessBoard[7][3] = new Queen(true,"Q");
        chessBoard[7][4] = new King(true,"K");
        chessBoard[7][5] = new Bishop(true,"B");
        chessBoard[7][6] = new Knight(true,"K");
        chessBoard[7][7] = new Rook(true,"R");
        chessBoard[6][0] = new Pawn(true,"P");
        chessBoard[6][1] = new Pawn(true,"P");
        chessBoard[6][2] = new Pawn(true,"P");
        chessBoard[6][3] = new Pawn(true,"P");
        chessBoard[6][4] = new Pawn(true,"P");
        chessBoard[6][5] = new Pawn(true,"P");
        chessBoard[6][6] = new Pawn(true,"P");
        chessBoard[6][7] = new Pawn(true,"P");   

        whiteKing = "7/4";
        blackKing = "0/4";

        check = false;
        resign = false;
    }

    public boolean validate(int r1, int c1, int r2, int c2, Piece[][] board, boolean turn)
    {
        if (!resign)
        {
            isInCheck(r1, c1, r2, c2, board, turn);
            isOutOfCheck(r1, c1, r2, c2, board, turn);

            if (board[r1][c1] == null)
                return false;

            if (board[r1][c1].getColor() != turn)
                return false;

            if (check)
                return false;

            if (board[r1][c1].canMove(r1, c1, r2, c2, board) && !(board[r1][c1] instanceof King))
            {
                
                
                board[r2][c2] = board[r1][c1];
                board[r1][c1] = null;
                
                if (board[r1][c1] instanceof Rook)
                    board[r1][c1].setHasMoved(true);
                    
                return true;            
            } 

            if (board[r1][c1] instanceof King)
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
                                board[r2][c2] = board[r1][c1];
                                board[r1][c1] = null; 

                                if (board[r2][c2].getColor())
                                    whiteKing = r2 + "/" + c2;
                                else
                                    blackKing = r2 + "/" + c2;

                                board[r2][c2].setHasMoved(true);

                                return true;
    
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    public Piece[][] getChessBoard()
    {
        return chessBoard;
    }

    public int promote(int r, int c, Piece[][] board)
    {
        if (board[r][c] instanceof Pawn)
        {
            if (board[r][c].getColor())
            {
                if (r == 0)
                {
                    board[r][c] = new Queen(true,"Q");
                    return 1;
                }
            }
            else 
            {
                if (r == 7)
                {
                    board[r][c] = new Queen(false,"Q");
                    return 2;
                }
            }
        }
        return 0;
    }

    public String[][] squaresTheKingCanMove(int r1, int c1, Piece[][] board) 
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
                if (board[r1][c1].canMove(r1, c1, r1 + i - 1, c1 + j - 1, board) && !checkSquare(r1, c1, r1 + i - 1 , c1 + j - 1, board))
                    moves[i][j] = (r1 + i - 1) + "/" + (c1 + j - 1);
            }  
        }

        return moves;
    }

    public boolean checkSquare(int r1, int c1, int sr, int sc, Piece[][] board)
    {
        
        if (checkDiagonals(r1, c1, sr, sc, board))
            return true;

        if (checkHorizontals(r1, c1, sr, sc, board))
            return true;

        if (checkKnights(r1, c1, sr, sc, board))
            return true;

        if (checkPawns(r1, c1, sr, sc, board))
            return true;

        if (checkKings(r1, c1, sr, sc, board))
            return true;

        return false;
    } 

    public void movePiece(int r1, int c1, int r2, int c2) //required for implementing castling code
    {
        if(chessBoard[r2][c2]==null)
        {
           chessBoard[r2][c2]=chessBoard[r1][c1]; 
           chessBoard[r1][c1]=null; 
        } 
    } 

    public Piece getPiece(int row, int col) 
    { 
        return chessBoard[row][col];
    }
  
    public boolean checkDiagonals(int r1, int c1, int sr, int sc, Piece[][] board)
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

            row += changerow;
            col += changecol;

            while (row >= 0 && row <= 7 && col >= 0 && col <= 7 && board[row][col] == null)
            {
                row += changerow;
                col += changecol;
            }

            if (row >= 0 && row <= 7 && col >= 0 && col <= 7)
            {
                if ((board[row][col] instanceof Bishop || board[row][col] instanceof Queen) && board[r1][c1].getColor() != board[row][col].getColor())
                    return true; 
            }

        }
        return false;
    }

    public boolean checkHorizontals(int r1, int c1, int sr, int sc, Piece[][] board)
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

            row += changerow;
            col += changecol;

            while (row >= 0 && row <= 7 && col >= 0 && col <= 7 && board[row][col] == null)
            {
                row += changerow;
                col += changecol;
            }

            if (row >= 0 && row <= 7 && col >= 0 && col <= 7)
            {
                if ((board[row][col] instanceof Rook || board[row][col] instanceof Queen) && board[r1][c1].getColor() != board[row][col].getColor())
                    return true; 
            } 
            
        }
        return false;
    }

    public boolean checkKnights(int r1, int c1, int sr, int sc, Piece[][] board)
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
                if (board[row][col] instanceof Knight && board[r1][c1].getColor() != board[row][col].getColor())
                    return true; 
            }
        }
        return false;
    }

    public boolean checkPawns(int r1, int c1, int sr, int sc, Piece[][] board)
    {
        int changerow;
        int changecol = 1;

        if (board[r1][c1].getColor())
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
                if (board[row][col] instanceof Pawn && board[r1][c1].getColor() != board[row][col].getColor())
                    return true; 
            }
        }
        return false;
    }

    public boolean checkKings(int r1, int c1, int sr, int sc, Piece[][] board)
    {
        int changerow;
        int changecol = -1;

        if (board[r1][c1].getColor())
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
                if (board[row][col] instanceof King && board[r1][c1].getColor() != board[row][col].getColor())
                    return true; 
            }
        }
        return false;
    }

    public void isInCheck(int r1, int c1, int r2, int c2, Piece[][] board, boolean turn)
    {
        int kr1;
        int kc1;
        if (turn)
        {
            kr1 = Integer.parseInt(whiteKing.substring(0, 1));
            kc1 = Integer.parseInt(whiteKing.substring(2, 3));
        }
        else 
        {
            kr1 = Integer.parseInt(blackKing.substring(0, 1));
            kc1 = Integer.parseInt(blackKing.substring(2, 3)); 
        }

        if (checkSquare(kr1, kc1, kr1, kc1, board)) 
        {            
            check = true;
            
        }
        else 
            check = false;
    }

    public void isOutOfCheck(int r1, int c1, int r2, int c2, Piece[][] board, boolean turn)
    {
        String tempWhiteKing = whiteKing;
        String tempBlackKing = blackKing;


        Piece[][] temp = new Piece[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                temp[i][j] = board[i][j];
            }
        }

        temp[r2][c2] = temp[r1][c1];

        if (r1 != r2 || c1 != c2)
            temp[r1][c1] = null; 

        if (temp[r2][c2] instanceof King)
        {
            if (temp[r2][c2].getColor())
                tempWhiteKing = r2 + "/" + c2;
            else
                tempBlackKing = r2 + "/" + c2;
        }

        int kr1;
        int kc1;

        if (turn)
        {
            kr1 = Integer.parseInt(tempWhiteKing.substring(0, 1));
            kc1 = Integer.parseInt(tempWhiteKing.substring(2, 3));
        }
        else 
        {
            kr1 = Integer.parseInt(tempBlackKing.substring(0, 1));
            kc1 = Integer.parseInt(tempBlackKing.substring(2, 3)); 
        }
        

        if (checkSquare(kr1, kc1, kr1, kc1, temp))             
            check = true;
        else 
            check = false;
    }

    public void setResign(boolean value)
    {
        resign = value;
    }

    public boolean getResign()
    {
        return resign;
    }

    public void setWhiteKing(String value)
    {
        whiteKing = value;
    }

    public void setBlackKing(String value)
    {
        blackKing = value;
    }

    public boolean getCheck()
    {
        return check;
    }
}