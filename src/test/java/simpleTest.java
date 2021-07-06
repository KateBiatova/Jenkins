import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class simpleTest {
    @Test
    public void simpleTest() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/index.php");

        WebElement signIn_link = driver.findElement(By.partialLinkText("Sign in"));
        Actions action = new Actions(driver);
        action.moveToElement(signIn_link).click().build().perform();
        driver.findElement(By.id("email")).sendKeys("test_test@mail.com");
        driver.findElement(By.id("passwd")).sendKeys("Test123456");
        driver.findElement(By.id("SubmitLogin")).click();

        WebElement customer_account_link = driver.findElement(By.xpath("//a[@class='account']"));
        Assert.assertEquals("Test Test", customer_account_link.getText());

        driver.quit();

    }
}