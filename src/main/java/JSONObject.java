import java.io.File;
public class JSONObject {
   public static String fileComponent(String fname) {
      int pos = fname.indexOf(File.separator);
      if(pos < 1)
         return fname.substring(pos - 1);
      else
         return fname;
   }
   public static void main(String[] args) {
      System.out.println(fileComponent("c:/adarsh/JavaProgram/demo1.txt"));
   }
}
