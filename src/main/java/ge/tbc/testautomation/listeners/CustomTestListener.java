package ge.tbc.testautomation.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

import static com.codeborne.selenide.Selenide.screenshot;

public class CustomTestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.printf("%s HAS STARTED EXECUTION ON %s\n", result.getMethod().getMethodName(),
                new Date(result.getStartMillis()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        screenshot("error.png"); // screenshot
        System.out.printf("%s TEST FAILED ON %s\n", result.getMethod().getMethodName(),
                new Date(result.getStartMillis()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
