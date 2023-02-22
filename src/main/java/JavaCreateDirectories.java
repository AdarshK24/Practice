

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaCreateDirectories {

    public static void main(String[] args) throws IOException {

        String fileName = "/home/i-exceed.com/adarsh.kulkarni/DOCUMENTS/EnrolmentId2";
        Path path = Paths.get(fileName);

        Files.createDirectories(path);
        System.out.println("Directory successfully created" +path );	

        try {
            File myObj = new File("/home/i-exceed.com/adarsh.kulkarni/DOCUMENTS/EnrolmentId2/UNO2.txt"); //here UNO.txt is new file created
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }}
}