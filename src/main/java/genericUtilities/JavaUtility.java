package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * this class consist of generic method related to java
 * @author Shubham
 */

public class JavaUtility {
	
	/**
	 * This method will generate random 
	 * number in every run and return into the caller
	 * @return
	 */
	public int getRandomNumber()
	{
		Random ran = new Random();
		int r = ran.nextInt(10000);
		return r;
	}
	/**
	 * this method will capture the current system date in required format
	 * @return
	 */
	public String getSystemDate() {
		Date d = new Date();
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String date = formatter.format(d);
		return date;
	}
	
	
	
	

}
