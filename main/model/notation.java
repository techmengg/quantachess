package main.model;

public class notation {
    private String [] alphabet = {"A","B","C","D","E","F","G","H"};
    private String [] numberStng = {"0","1","2","3","4","5","6","7","8"};
    private String [][] notationalBoard = new String[9][9];


    for (int c = 0 ; c < notationalBoard.size ; c ++  ) {

        notationalBoard[0][c] =  numberStng[c];

    }

    for (int r = 0 ; r < notationalBoard.size ; r ++  ) {

        notationalBoard[r][0] =  alphabet[r];

    }


}
