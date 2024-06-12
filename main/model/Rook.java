package main.model;


public class Rook extends Piece
{ 
    public static boolean hasMoved; 
    public Rook(boolean color)
    {
        super(color);  
        hasMoved = false; 
          
    } 

    public  boolean gethasMoved()  
    {
        return hasMoved;
    }  

    

    //rows and columns are reversed wtfff 
    //turn is a substitute for using the colour variable and having to use a getcolour method. 
    public static int castle(int r1, int c1, int r2, int c2, Piece[][] chessboard,boolean turn) //castling method 
    {  
         //returns 0 for false 
         //returns 1 for kingside castle 
         //retunrs 2 for queenside castle 
        if(!chessboard[r1][c1].gethasMoved()&& chessboard[r1][c1] instanceof Rook)  //hasmoved check for rook peice 
        {  
            if(chessboard[r2][c2]!= null)
            {
            if(!chessboard[r2][c2].gethasMoved() && chessboard[r2][c2] instanceof King) //hasmoved check for king 
            {   
                if(chessboard[r1][c1].getColor()==chessboard[r2][c2].getColor() && turn==chessboard[r1][c1].getColor()) 
                {
                 int space = Math.abs(c2-c1);       
                    if(space ==4) //logic for queenside castling 
                    {
                        int i = c2-1; 
                        while(i>=1)
                        {  
                            if(chessboard[r1][i]!=null)
                            {
                                
                                return 0; 
                                
                            } 
                            i--;  
                        } 
                           
                         return 2;  
                         
                         
                    } 
                    else if(space== 3)// logic for kingside castling
                    { 
                        int  i= c1 -1; 
                        while(i>=5)
                        { 
                            if(chessboard[r1][i]!=null)
                            { 
                                
                                return 0; 
                            }
                            i--; 

                        }  
                       
                        return 1; 
                    }
                }
             }
            }  
            
        }


        return 0; 
    } 

    public boolean canMove(int r1, int c1, int r2, int c2, Piece[][] chessboard) 
    {   

        //add a condition like if the peices are the same colour and if first peice is rook and second is king 
        if(chessboard[r1][c1].getColor()==chessboard[r2][c2].getColor())
        {
            return false;
        }
        hasMoved = true;
        return true;  
         

public class Rook extends Piece {
    public Rook(boolean color) {
        super(color);

    }

    public boolean canMove(int r1, int c1, int r2, int c2, Piece[][] chessboard) {
        if (r1 == r2 && c1 == c2) {
            return false; // Same position
        }

        if (chessboard[r2][c2] != null) {
            if (chessboard[r1][c1].getColor() == chessboard[r2][c2].getColor())
            {
                return false;
            }
        }

        if (r1 == r2) { // Check for horizontal movement
            return isHorizontalClear(r1, c1, c2, chessboard);
        }

        if (c1 == c2) { // Check for vertical movement
            return isVerticalClear(r1, c1, r2, chessboard);
        }
        return false; // Queen cannot move to the specified position
    }

    private boolean isHorizontalClear(int r, int c1, int c2, Piece[][] chessboard) {
        int minC = Math.min(c1, c2);
        int maxC = Math.max(c1, c2);
        for (int i = minC + 1; i < maxC; i++) {
            if (chessboard[r][i] != null) {
                return false; // Path is blocked
            }
        }
        return true; // Path is clear
    }

    private boolean isVerticalClear(int r1, int c, int r2, Piece[][] chessboard) {
        int minR = Math.min(r1, r2);
        int maxR = Math.max(r1, r2);
        for (int i = minR + 1; i < maxR; i++) {
            if (chessboard[i][c] != null) {
                return false; // Path is blocked
            }
        }
        return true; // Path is clear
    }

    
}