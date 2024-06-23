package main.model;
import java.io.File; 
import java.io.FileWriter; 
import java.util.ArrayList; 

public class saveToFile {

    private String fileLocation = "";
    private static ArrayList<String> allFileLocations = new ArrayList<String>();

    public saveToFile(String location) {

        fileLocation = location;


    }

    public void filegeneration( ) {
        
        File savesFolder = new File(
            "main/Saves");
        String location = savesFolder.toString();


        allFileLocations.add(import java.io.FileWriter; );



        
    }

    


    public void writeNotationtoFile (String notationFormat) throws IOException  {


        FileWriter fileWriter = new FileWriter(null);

    }

}   
