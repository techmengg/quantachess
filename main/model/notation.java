package main.model;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//import javax.swing.JFrame;
//import javax.swing.JLabel;





public class notation {
    private static String [] alphabet = {"N","A","B","C","D","E","F","G","H"};
    private static String [] numberStng = {"0","8","7","6","5","4","3","2","1"};
    private static String [][] notationalBoard = new String[9][9];
    
    private static String  notationFormatG = "";
    private static int progression = 0;
    private static String gameName = "";
    private static String whiteName = "";
    private static String blackName = "";
    private static String hosted = "";
    private static String date = "";
    private static String notationFormatS = "";
     

    
    public static void createRef() {
        for (int c = 1 ; c < notationalBoard[0].length ; c ++  ) {


            notationalBoard[0][c] =  numberStng[c];
            //System.out.println(notationalBoard[0][c]);
    
        }
    
        for (int r = 1 ; r < notationalBoard.length ; r ++  ) {
    
            notationalBoard[r][0] =  alphabet[r];
           // System.out.println(notationalBoard[r][0]);
        }
    } 
  

   

    public static void pawnPromo(int row,int col) {
        String pos = notationalBoard[row + 1][col + 1 ];
        notationFormatG += pos +"=Q";



    }
    //Use after and or during player move
    public static void moveOcc(int selectedRow,int selectedCol, String type, boolean capture ) {


        String moveTo = notationalBoard[selectedRow][selectedCol];
       String piece = type;
        String taken ="";
        if (type == "P") {
            piece ="";
        }
        if (capture == true) {
            taken = "x";
            
        }

        notationFormatG += piece + taken  + moveTo + " ";




    }
    //Use when castling occurs
    public static void castlingOcc(boolean kingSide) {


        if (kingSide == false) {


            notationFormatG += "O-O ";




        }


        else if (kingSide == true) {


            notationFormatG += "O-O-O ";




        }


    }

    public static void winning(boolean side ) {
        if (side == true) {

            //White Side
            notationFormatS += "[Result:" + " '" + "1-0" + "']\n";

        }
        else {

            //Black Side
            notationFormatS += "[Result:" + " '" + "0-1" + "']\n";
        }
    }
    //Use at end of check method (If True)
    public static void checkOcc(boolean checkMate) {


       
        if (checkMate == true) {
            notationFormatS += "#";




        }
        else {
            notationFormatS += "+";


        }


    }


    //Use After All moves are done
    public static  void nextPhase() {
        progression ++;


    }
    //Use Before Moves are added
    public static void phase() {
        notationFormatG += progression + ". ";
    }




    public  static void captureOcc() {


        notationFormatG += "x";


    }

    public static void grabStartDetails(String event, String site, String nameB, String nameW , String dateOccur) {
        gameName = event;
        hosted  = site;
        date = dateOccur;
        whiteName = nameW;
        blackName = nameB;

        StartScreen.ended(true);

        notation.addStartdetails();

    }

    public static void addStartdetails() {
       notationFormatS += "[Event:" + " '" + gameName + "']\n" + "[Site:" + " '" + hosted + "']\n" + "[Date:" + " '" + date + "']\n" 
       + "[White:" + " '" + whiteName + "']\n" + "[Black:" + " '" + blackName + "']\n";
      // System.out.print(gameName + " " + hosted + " " + date + " " + whiteName + " " + blackName);
    
    }

    public static void addTurns() {
        notationFormatS += "[Rounds:" + " '" + progression + "']\n";

    }

    public static String getGameName() {
        return gameName;
    }
    public static void reset() {
        notationFormatG = "";
        notationFormatS = "";
        progression = 0;
        gameName = "";
        whiteName = "";
        blackName = "";
        hosted = "";
        date = "";
    }

    public static void importToTextFile() throws IOException {
        String fileLocation = saveToFile.getLocation();
        saveToFile.writeNotationtoFile(notationFormatS, notationFormatG, fileLocation );

    }
} 