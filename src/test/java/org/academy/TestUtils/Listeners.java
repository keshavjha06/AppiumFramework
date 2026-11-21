package org.academy.TestUtils;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.AttachmentOptions;

/**
 * Attaches a screenshot to the Allure report when a test fails.
 * Allure's own TestNG listener (io.qameta.allure.testng.AllureTestNg) is registered
 * automatically via ServiceLoader and records the test results and steps.
 *
 * The screenshot is taken in afterInvocation (not ITestListener#onTestFailure) because
 * Allure closes the test result in its own onTestFailure, after which attachments
 * no longer get linked to the test.
 */
public class Listeners implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (!method.isTestMethod() || result.getStatus() != ITestResult.FAILURE) {
            return;
        }
        try {
            AppiumDriver driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
            if (driver != null) {
                byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
                Allure.attachment(result.getMethod().getMethodName() + " - failure screenshot",
                        "image/png", new ByteArrayInputStream(screenshot), AttachmentOptions.withFileExtension("png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
