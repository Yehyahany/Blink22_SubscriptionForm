package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_HomePage {
    private final By BlogButton = By.xpath("//*[@id=\"__next\"]/div/nav/div/nav[1]/ul/li[5]/a");
    private final WebDriver driver;

    public P01_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public P02_BlogPage ClickOnBlogButton(){
        Utility.clickOnElement(driver,BlogButton);
        return new P02_BlogPage(driver);
    }

}
