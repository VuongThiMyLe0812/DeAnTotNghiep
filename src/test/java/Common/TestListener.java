//package Common;
//
//import io.qameta.allure.Attachment;
//import io.qameta.allure.Step;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//public class TestListener implements ITestListener {
//    @Override
//    public void onTestFailure(ITestResult result) {
//        Object testClass = result.getInstance();
//        WebDriver driver = ((BaseTest) testClass).getDriver();
//        if (driver != null) {
//            saveScreenshot(driver);
//        }
//    }
//    @Attachment(value = "Screenshot on Failure", type = "image/png")
//    public byte[] saveScreenshot(WebDriver driver) {
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        Object testClass = result.getInstance();
//        WebDriver driver = ((BaseTest) testClass).getDriver();
//        if (driver != null) {
//            saveScreenshot(driver);
//        }
//    }
//    @Step("Chụp màn hình tại bước: {stepName}")
//    @Attachment(value = "Screenshot", type = "image/png")
//    public byte[] captureStep(WebDriver driver, String stepName) {
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }

//}

package Common;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();

        if (driver != null) {
            saveScreenshot(driver);
        }
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.out.println("Không thể chụp màn hình: " + e.getMessage());
            return null;
        }
    }
}
