package main.model;

public class Rook extends Piece
{ 
    public static boolean hasMoved; 
    public Rook(boolean color)
    {
        super(color); 
        hasMoved = false; 
    }

    public static boolean castle(int r1, int c1, int r2, int c2, Piece[][] chessboard) 
    {  
         
        if(!hasMoved) //hasmoved check for rook peice
        {   
            if(!chessboard[r2][c2].gethasMoved()) 
            {   
                if(chessboard[r1][c1].getColor()==chessboard[r2][c2].getColor()) 
                {
                 int space = Math.abs(r2-r1);     
                    if(space ==4) //logic for queenside castling
                    {
                        int i = r2; 
                        while(i>=0)
                        {
                            if(chessboard[i][0]!=null)
                            {
                                return false;
                            } 
                            i--; 
                        }
                         

                    } 
                    else 
                    {

                    }
                }
             }
        }


        return true; 
    } 

    public boolean canMove(int r1, int c1, int r2, int c2, Piece[][] chessboard) 
    {   


        return true;
    }
}