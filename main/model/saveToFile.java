package main.model;
import java.io.File; 
import java.io.FileWriter; 
import java.util.ArrayList; 
import java.io.IOException;
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

        //Saves Directory of newly created file 
        allFileLocations.add(location);



        
    }

    public String getLocation() {

        return fileLocation;
    }


    public void writeNotationtoFile (String notationFormat, String fileDirectory) throws IOException {


        FileWriter fileWriter = new FileWriter(fileDirectory);

    }

}   
