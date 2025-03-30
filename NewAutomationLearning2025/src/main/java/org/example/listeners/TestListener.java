package org.example.listeners;

import org.example.utils.ExtentReportManager;
import org.example.utils.ExtentTestManager;
import org.example.utils.ScreenshotUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("[INFO] Test Suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("[INFO] Test Suite finished: " + context.getName());
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[INFO] Test started: " + result.getMethod().getMethodName());
//        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
//        extentTest.set(test);
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        ExtentTestManager.setTest(test); // 👈 store current test in threadlocal
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[PASS] " + result.getMethod().getMethodName());
        extentTest.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[FAIL] " + result.getMethod().getMethodName());
        extentTest.get().fail(result.getThrowable());

        String screenshotPath = ScreenshotUtils.captureScreenshot(result.getMethod().getMethodName());
        extentTest.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[SKIPPED] " + result.getMethod().getMethodName());
        extentTest.get().skip("Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not commonly used, can be left empty
    }
}
