package org.academy.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import io.appium.java_client.remote.AppiumWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {

    AndroidDriver driver;

    public AndroidActions(AndroidDriver driver) {

        this.driver = driver;
    }

    public void longPressAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((AppiumWebElement) ele).getId(),
                        "duration", 2000));
    }


    /**
     * Starts an Android activity. java-client 9+ removed AndroidDriver#startActivity(Activity),
     * so this uses the UiAutomator2 "mobile: startActivity" extension instead
     */
    public void startActivity(String appPackage, String appActivity) {
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity",
                ImmutableMap.of(
                        "intent", appPackage + "/" + appActivity,
                        "stop", true,   // force-stop the app first (old startActivity default)
                        "wait", true)); // block until the activity has launched
    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0

            ));
        } while (canScrollMore);
    }

    public void scrollToText(String text) {

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
    }


    public void swipeAction(WebElement ele, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((AppiumWebElement) ele).getId(),

                "direction", direction,
                "percent", 0.75
        ));


    }

}
