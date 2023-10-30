package genericUtilities;

import org.testng.ITestResult;

/**
 * This class will provide implementation to the IRetryAnalyzer of TestNG
 * @author Shubham
 */
public class RetryAnalyzerImplimentation {
	int count=0;
	int retryCount=3;
	public boolean retry(ITestResult result) {
		while(count<retryCount) {
			count++;
			return true;
		}
		
		
		return false;
		
	}

}
