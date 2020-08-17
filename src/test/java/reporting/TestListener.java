package reporting;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class TestListener implements ITestListener {


    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
        ExtentTestManager.startTest(context.getName());
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        //ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, result.getMethod().getMethodName()+": Test passed");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
        try {
            String base = getScreenshot(driver, result.getName());
            ExtentTestManager.getTest().addScreenCaptureFromBase64String(base, "Screen");
            ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, result.getMethod().getMethodName()+ ": "+"Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination;
        if (System.getProperty("os.name").startsWith("Windows")){
            destination = System.getProperty("user.dir") + "/TestReport/" + screenshotName + dateName + ".png";
        } else {
            destination = System.getProperty("user.dir") + "/TestReport/" + screenshotName + dateName + ".png";
        }
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        byte[] fileContent = FileUtils.readFileToByteArray(new File(destination));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        return encodedString;
    }

}