package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class P02_BlogPage {
    private final WebDriver driver;
    private By name = By.id("fullname");
    private By email = By.id("email");
    private By subscribebutton = By.id("_form_5_submit");
    private By head = By.className("BlogLayout_formTitle__PiIaQ");
    private By thanksmessage = By.className("_form-thank-you");
    private By errormessage = By.className("_error-inner");

    public P02_BlogPage(WebDriver driver) {
        this.driver = driver;
    }
    public P02_BlogPage getPlaceholders(){
        Utility.findWebElement(driver,head);
        System.out.println("Name Placeholder is: "+driver.findElement(name).getAttribute("placeholder"));
        System.out.println("Mail Placeholder is: "+driver.findElement(email).getAttribute("placeholder"));
        return this;
    }
    public P02_BlogPage enterdata(String nametxt,String mailtxt){
        Utility.findWebElement(driver,head);
        Utility.sendData(driver,name,nametxt);
        Utility.sendData(driver,email,mailtxt);
        return this;
    }
    public P02_BlogPage clickOnSubscribe(){
        Utility.scrolling(driver,head);
        Utility.clickOnElement(driver,subscribebutton);
        return this;
    }
    public String thanksmessage(){
        return Utility.GetText(driver,thanksmessage);
    }
    public boolean thanksmessageisVisible(){
        System.out.println("Thanks message: "+Utility.GetText(driver,thanksmessage));
        return driver.findElement(thanksmessage).isDisplayed();
    }
    public boolean errormessageisVisible(){
        System.out.println("Eror46: "+ driver.findElement(errormessage).isDisplayed());
        System.out.println("Error message: "+Utility.GetText(driver,errormessage));
        return driver.findElement(errormessage).isDisplayed();
    }
}
