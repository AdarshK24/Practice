import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

class Demo {
	public static void main(String[] args) {
		String directoryPath = "/home/i-exceed.com/adarsh.kulkarni/jboss-eap-7.0/uploads/wageloss/E98562145/postman.png";

		
		Path path = Paths.get(directoryPath);
		String directory = path.getParent().toString();
		String news = directory.concat("/");
		System.out.println(news);
//		String content = "is not like is, but mistakes are common";
//		String regex = "\\s*\\bis\\b\\s*";
//		content = content.replaceAll(regex, "");
//		System.out.println(content);
		
		
		
try {
		File file = new File(news);
//		System.out.println(file.toPath());
//		System.out.println(directoryPath);
		if (file.isDirectory()) {
			System.out.println("File is a Directory");
		} else {
			System.out.println("Directory doesn't exist!!");
		}
	}catch(Exception e) {
		System.out.println("");
	}
	}
	
}

////	      Date date = new Date();
////	      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
////	       String str = formatter.format(date);
////	      System.out.print("Current date: "+str);
//		   
//		   
//		// Converts the string
//	        // format to date object
//	        DateFormat df = new SimpleDateFormat(date);
//	  
//	        // Get the date using calendar object
//	        Date today = Calendar.getInstance()
//	                         .getTime();
//	  
//	        // Convert the date into a
//	        // string using format() method
//	        String dateToString = df.format(today);
//	  
//	        // Return the result
//	        return (dateToString);

//	      String dt = "2008-01-06";  // Start date
//	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	      Calendar c = Calendar.getInstance();
//	      c.setTime(sdf.parse(dt));
//	      c.add(Calendar.DATE, 1);  // number of days to add
//	      dt = sdf.format(c.getTime());  // dt is now the new date
//	      System.out.println(dt);

//		   Date today = new Date();               
//		   SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");            
//		   Calendar c = Calendar.getInstance();        
//		   c.add(Calendar.DATE, 1);  // number of days to add      
//		   String tomorrow = (String)(formattedDate.format(c.getTime()));
//		   System.out.println("Tomorrows date is " + tomorrow);
// Creating a Calendar object
//	        Calendar c1 = Calendar.getInstance();

// Set Month
// MONTH starts with 0 i.e. ( 0 - Jan)
//	        c1.set(Calendar.MONTH, 00);

// Set Date
//	        c1.set(Calendar.DATE, 30);

// Set Year
//	        c1.set(Calendar.YEAR, 2019);

// Creating a date object
// with specified time.
//	        Date dateOne = c1.getTime();
//	  
//	        Instant inst = dateOne.toInstant();
//	  
//	        System.out.println(
//	            "Original Date: "
//	            + dateOne.toString());
//	        System.out.println(
//	            "Instant: " + inst);
//	        String tid = "2022-08-09";
//	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        Date date = new Date();
//			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String start_date = sdf1.format(date);
//			String end_date = sdf1.format("2022-02-09");

//			Date d1 = sdf.parse(start_date.toString());
//		Date d2 = sdf.parse(tid);
//			long difference_In_Time = d2.getTime() - d1.getTime();
//			long difference_In_Seconds = (difference_In_Time / 1000) % 60;
//			long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
//			long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
//			long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
//			long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
//			long days = difference_In_Days + (difference_In_Years * 365);
//System.out.println(""+tid);
