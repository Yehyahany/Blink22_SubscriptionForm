
import Pages.P01_HomePage;
import Pages.P02_BlogPage;
import Utilities.DataUtils;
import Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.Duration;

public class SubscribeTCs {
    private WebDriver driver;

    @BeforeMethod
    public void Setup() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(DataUtils.getPropertyValue("Environments","HomeURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test (priority = 0)
    public void ValidSubscriptionTest() throws IOException {
        boolean thankmessagevisibility = new P01_HomePage(driver).ClickOnBlogButton().getPlaceholders()
                .enterdata(DataUtils.getJsonData("ValidData","name"),DataUtils.getJsonData("ValidData","email"))
                .clickOnSubscribe()
                .thanksmessageisVisible();
        Assert.assertEquals(driver.getCurrentUrl(),DataUtils.getPropertyValue("Environments","BlogURL"));
        Assert.assertTrue(thankmessagevisibility);
    }
    @Test (priority = 1)
    public void NoDataTest(){
        new P01_HomePage(driver).ClickOnBlogButton().clickOnSubscribe();
        Assert.assertTrue(new P02_BlogPage(driver).errormessageisVisible());
    }
    @Test (priority = 2)
    public void NoNameTest() throws IOException{
        new P01_HomePage(driver).ClickOnBlogButton().
                enterdata(DataUtils.getJsonData("inValidData","empty"),DataUtils.getJsonData("ValidData","email"))
                .clickOnSubscribe();
        Assert.assertTrue(new P02_BlogPage(driver).errormessageisVisible());
    }
    @Test (priority = 3)
    public void NoEmailTest() throws IOException{
        new P01_HomePage(driver).ClickOnBlogButton().
                enterdata(DataUtils.getJsonData("inValidData","name"),DataUtils.getJsonData("inValidData","empty"))
                .clickOnSubscribe();
        Assert.assertTrue(new P02_BlogPage(driver).errormessageisVisible());
    }
    @Test (priority = 4)
    public void NumbersAsNameTest() throws IOException{
        boolean thankmessagevisibility = new P01_HomePage(driver).ClickOnBlogButton().getPlaceholders()
                .enterdata(DataUtils.getJsonData("inValidData","numbers"),DataUtils.getJsonData("ValidData","email"))
                .clickOnSubscribe()
                .thanksmessageisVisible();
        Assert.assertFalse(thankmessagevisibility);
    }
    @Test (priority = 5)
    public void SpecialcharacAsNameTest() throws IOException{
        boolean thankmessagevisibility = new P01_HomePage(driver).ClickOnBlogButton().getPlaceholders()
                .enterdata(DataUtils.getJsonData("inValidData","specialchar"),DataUtils.getJsonData("ValidData","email"))
                .clickOnSubscribe()
                .thanksmessageisVisible();
        Assert.assertFalse(thankmessagevisibility);
    }
    @Test (priority = 6)
    public void InvalidmailformatTest () throws IOException{
        new P01_HomePage(driver).ClickOnBlogButton().getPlaceholders()
                .enterdata(DataUtils.getJsonData("ValidData","name"),DataUtils.getJsonData("inValidData","invalidemail"))
                .clickOnSubscribe();
        Assert.assertTrue(new P02_BlogPage(driver).errormessageisVisible());
    }
    @AfterMethod
    public void quit(){
        driver.quit();
    }
}
