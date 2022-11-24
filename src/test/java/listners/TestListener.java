package listners;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import report.ExtentManager;

import static report.ExtentTestManager.getTest;

public class TestListener implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
      //  iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println(getTestMethodName(iTestResult) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        getTest().log(Status.PASS, "Test passed");
    }


    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(getTestMethodName(iTestResult) + " test is failed.");
        getTest().log(Status.FAIL, "Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }


}