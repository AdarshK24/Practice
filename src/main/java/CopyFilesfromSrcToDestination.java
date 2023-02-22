import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;

public class CopyFilesfromSrcToDestination {
	public static void main(String[] args) throws IOException {

//		String Filename = "/home/i-exceed.com/adarsh.kulkarni/DOCUMENTS/adarshK";
		String Filename = "/home/i-exceed.com/adarsh.kulkarni/jboss-eap-7.0/uploads/wageloss/E632514895562";
		String NewDirectory = createDirectory(Filename);
//		System.out.println(NewDirectory);
//		String concat = NewDirectory.concat("/memberId/file");
//		System.out.println(concat);
		String concat = NewDirectory.concat("/");
		// here output of above line is ==> A directory is created by the name
		// Enrolment5
//		
//		
//		File source = new File("/home/i-exceed.com/adarsh.kulkarni/DOCUMENTS/EnrolmentId2/MicrosoftTeams-image.png");
//		File destin = new File(concat);
//
//		copyFiles(source, destin);
		  //absolute path for source file to be copied
        String source = "/home/i-exceed.com/adarsh.kulkarni/jboss-eap-7.0/uploads/wageloss/refr/error1.png";
        //directory where file will be copied
        String target =concat.toString();
     
        //name of source file
        File sourceFile = new File(source);
        String name = sourceFile.getName();
     
        File targetFile = new File(target+name);
        System.out.println("Copying file : " + sourceFile.getName() +" from Java Program");
     
        //copy file from one location to other
        copyFile(sourceFile, targetFile);
     
        System.out.println("copying of file from Java program is completed");
    }


	

	private static void copyFiles(File sourceFile, File targetFile)  {
		// This is safe and don't corrupt files as FileOutputStream does
		File src = sourceFile;
		try {
			FileUtils.copyFile(sourceFile, targetFile,StandardCopyOption.COPY_ATTRIBUTES);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "resource", "unused" })
	private static void copyFile(File sourceFile, File destFile) throws IOException {
		if (!sourceFile.exists()) {
			return;
		}
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = null;
		FileChannel destination = null;
		source = new FileInputStream(sourceFile).getChannel();
		destination = new FileOutputStream(destFile).getChannel();
		if (destination != null && source != null) {
				  try{
					  FileWriter fstream = new FileWriter("log.txt",true);
					  BufferedWriter out = new BufferedWriter(fstream);
					  out.write("Line Added on: " + new java.util.Date()+"\n");
					  out.close();
				  }catch (Exception e){
					 System.err.println("Error while writing to file: " +
				          e.getMessage());
				  }
				  
			destination.transferFrom(source, 0, source.size());
		}
		if (source != null) {
			source.close();
		}
		if (destination != null) {
			destination.close();
		}

	}

	public static String createDirectory(String filename) {
		Path path = Paths.get(filename);

		try {
			Files.createDirectories(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Directory successfully created :::::" + path);
		return path.toString();
	}
}
