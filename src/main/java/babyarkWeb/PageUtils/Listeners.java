package babyarkWeb.PageUtils;



import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;



 public class Listeners implements ITestListener{
	 
	 ExtentTest test;
	 ExtentReports extent = ExtentReporterNG.getReporterObject();
	 WebDriver driver;
	 
	  @Override
	public void onTestStart(ITestResult result) {
		 test = extent.createTest(result.getMethod().getMethodName());
	  }

	 
	  @Override
		public void onTestSuccess(ITestResult result) {
	    test.log(Status.PASS, "Test Passed");
	  }

	 
	  @Override
		public void onTestFailure(ITestResult result) {
	    
	       test.fail(result.getThrowable());
	       
		   
		    try {
		    	//get driver
				driver = (AppiumDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
				
				 PageBase pageBase = new PageBase(driver);
				//add screenshot
				test.addScreenCaptureFromPath(pageBase.takeScrenshotAndReturnFilePath
						(result.getMethod().getMethodName()),result.getMethod().getMethodName());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  }
	

	 
	  @Override
		public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	
	  @Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	
	  @Override
		public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	
	  @Override
		public void onStart(ITestContext context) {
	    // not implemented
	  }

	 
	  @Override
		public void onFinish(ITestContext context) {
			extent.flush();
	  }
	}
	 
 
