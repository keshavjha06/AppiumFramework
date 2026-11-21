package org.academy.pageObjects.ios;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import io.appium.java_client.support.PageFactory;
import org.academy.utils.IOSActions;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertViews extends IOSActions {


    IOSDriver driver;

    //GrandParent(AppiumUtils) -> IOSActions -> AlertViews
    public AlertViews(IOSDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    //driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label =='Text Entry'`]")).click();
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label =='Text Entry'`]")
    private WebElement textEntryMenu;

    //driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")).click();
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")
    private WebElement confirmMenuItem;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
    private WebElement textBox;

    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement acceptPopUp;

    @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH[c] 'A message'")
    private WebElement confirmMessage;

    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
    private WebElement submit;


    @Step("Enter \"{name}\" in Text Entry alert")
    public void fillTextLabel(String name) {

        textEntryMenu.click();
        textBox.sendKeys(name);
        acceptPopUp.click();

    }

    @Step("Open Confirm alert and read message")
    public String getConfirmMessage() {
        confirmMenuItem.click();
        return confirmMessage.getText();
    }


}
