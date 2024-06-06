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
        if(!chessboard[r1][c1].gethasMoved()) //hasmoved check for rook peice 
        System.out.println(chessboard[r1][c1].gethasMoved()+","); 
        
        {  
            if(chessboard[r2][c2]!= null)
            {
            if(!chessboard[r2][c2].gethasMoved()) //hasmoved check for king 
            {   
                if(chessboard[r1][c1].getColor()==chessboard[r2][c2].getColor() && turn==chessboard[r1][c1].getColor()) 
                {
                 int space = Math.abs(c2-c1);     
                 //System.out.println(c1);  
                 //System.out.println(c2);
                 //System.out.println(space); 
                    if(space ==4) //logic for queenside castling
                    {
                        int i = c2-1; 
                        while(i>=1)
                        {  
                            System.out.println("i:"+i+" r1:"+r1);
                            if(chessboard[i][r1]!=null)
                            {
                                System.out.println("False");
                                return 0; 
                                
                            } 
                            i--;  
                        } 
                         return 2;  
                         
                         
                    } 
                    else // logic for kingside castling
                    { 
                        int  i= c1 +1; 
                        while(i<=6)
                        { 
                            System.out.println("i:"+i+" r1:"+r1);
                            if(chessboard[i][r1]!=null)
                            { 
                                System.out.println("False");
                                return 0; 
                            }
                            i++; 

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
        return true;
    }
}