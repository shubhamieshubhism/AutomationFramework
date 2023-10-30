import org.testng.annotations.Test;

public class ReadDataFromCmdLine {
	@Test
	public void read() {
		String USERNMAE = System.getProperty("username");
		String PASSWORD = System.getProperty("password");
		
		System.out.println(USERNMAE);
		System.out.println(PASSWORD);
	}

}
