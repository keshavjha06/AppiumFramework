package org.academy;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.academy.TestUtils.AndroidBaseTest;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class Ecommerce2 extends AndroidBaseTest {


    @BeforeMethod
    public void preSetup() {
        //screen to home page
        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
        driver.startActivity(activity);
    }

    @Test
    public void fillForm_errorValidation() {
        //	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Keshav");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        AssertJUnit.assertEquals(toastMessage, "Please your name");
    }


    @Test
    public void fillForm_positiveFlow() {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Keshav");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        AssertJUnit.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size() < 1);
    }
}
