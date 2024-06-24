package main.model;
import java.io.File; 
import java.io.FileWriter; 
import java.io.PrintWriter; 
import java.util.ArrayList; 
import java.io.IOException;


public class saveToFile {

    private static String fileLocation = "";
    private static ArrayList<String> allFileLocations = new ArrayList<String>();
    private static int gameNum = 1;
    

    public static void filegeneration( ) {
        String fileName = "Game" + gameNum;
        String folderDirectory = "main/Saves/";
        File savesFolder = new File(
            folderDirectory + fileName);
            savesFolder.exists();
            try {
                boolean isFileCreated = savesFolder.createNewFile();
                if (isFileCreated) {
                    System.out.println("File Created");
                }
                else {
                    System.err.println("Already Exists");
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            fileLocation = savesFolder.toString();

        //Saves Directory of newly created file 
        allFileLocations.add(fileLocation);
        gameNum++;


        
    }

    public static String getLocation() {

        return fileLocation;
    }



    public String getSpecificFile(int index) {

        return allFileLocations.get(index);

    }

    public int getFileArraySize() {
        return allFileLocations.size();
    }

    public static void writeNotationtoFile (String notationFormatStart,String notationFormatGame, String fileDirectory) throws IOException {


        FileWriter fileWriter = new FileWriter(fileDirectory);
        PrintWriter printWriter = new PrintWriter(fileWriter); 
        printWriter.printf(notationFormatStart +  notationFormatGame);

        printWriter.close();

    }

}   
