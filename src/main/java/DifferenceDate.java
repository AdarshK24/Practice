import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DifferenceDate {
	private static final String NUMBER = "0123456789";
	private static final Random RANDOM = new SecureRandom();
	public static void main(String[] args) {
	try {
			StringBuilder sb = new StringBuilder();
			int length = 11;
			int spacing = 0;
			int spacer = 0;
			while (length > 0) {
				if (spacer == spacing) {
					sb.append('E');
					spacer = 0;
				}
				length--;
				spacer++;
				sb.append(randomChar());
			}
			
			System.out.println(sb.toString());
		}

		// Catch the Exception
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	static char randomChar() {
		return NUMBER.charAt(RANDOM.nextInt(NUMBER.length()));
	}
}
