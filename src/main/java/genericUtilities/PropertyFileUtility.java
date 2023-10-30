package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

//coment
	/*multiline comment*/
	/**
	 * This class consist of generic method to read data from 
	 * property file
	 * @author Shubham 
	 */

public class PropertyFileUtility {
	/**
	 * This method will read data from property file and return the value to 
	 * caller
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	
public String readDataFrompropertyFile(String key) throws Throwable
{
	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
	Properties p = new Properties();
	p.load(fis);
	String value=p.getProperty(key);
	return value;
	
}
}
