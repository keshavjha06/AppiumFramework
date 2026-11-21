package org.academy.pageObjects.android;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.support.PageFactory;
import org.academy.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {

    AndroidDriver driver;

    public FormPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
    // driver.findElement(By.id("com.androidsample.generalstore:id/nameField"))

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOption;
    // driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']"))

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
    private WebElement maleOption;

    @AndroidFindBy(id = "android:id/text1")
    private WebElement countrySelection;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    @Step("Enter name \"{name}\"")
    public void setNameField(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();

    }

    @Step("Launch General Store home screen")
    public void setActivity() {
        startActivity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
    }

    @Step("Select gender \"{gender}\"")
    public void setGender(String gender) {
        if (gender.contains("female"))
            femaleOption.click();
        else
            maleOption.click();

    }

    @Step("Select country \"{countryName}\"")
    public void setCountrySelection(String countryName) {
        countrySelection.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();

    }

    @Step("Submit form (Let's Shop)")
    public ProductCatalogue submitForm() {
        shopButton.click();
        return new ProductCatalogue(driver);

    }

}
