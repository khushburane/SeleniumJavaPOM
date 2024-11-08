package extentReportNG;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    public static ExtentReports extent;
    public static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(LogStatus.PASS, "Test Passed");
        extent.endTest(test);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(LogStatus.FAIL, "Test Failed");
        test.log(LogStatus.FAIL, result.getThrowable());
        extent.endTest(test);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(LogStatus.SKIP, "Test Skipped");
        extent.endTest(test);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        extent.close();
    }
}
