package suite1;

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

public class homeworkTest {
    @Test
    public void homeworkTest() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/index.php");

        //find "Blouse" product
        driver.findElement(By.id("search_query_top")).click();
        driver.findElement(By.id("search_query_top")).sendKeys("Blouse");
        driver.findElement(By.name("submit_search")).click();

        //add the product to the cart
        WebElement blouse = driver.findElement(By.xpath("//a[@class='product_img_link']"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement addToCart = driver.findElement(By.xpath("//a[@class='button ajax_add_to_cart_button btn btn-default']"));

        Actions action = new Actions(driver);
        action.moveToElement(blouse).build().perform();
        wait.until(ExpectedConditions.visibilityOf(addToCart));
        action.moveToElement(addToCart).click().build().perform();

        //check if the product was added to the cart
        WebElement closePopup = driver.findElement(By.xpath("//span[@class='cross']"));
        wait.until(ExpectedConditions.elementToBeClickable(closePopup));
        WebElement productAdded = driver.findElement(By.xpath("//div[(@class='layer_cart_product col-xs-12 col-md-6')]/h2"));
        Assert.assertEquals("Product successfully added to your shopping cart", productAdded.getText());

        WebElement itemInYourCart = driver.findElement(By.xpath("//span[contains(text(),'There is 1 item in your cart.')]"));
        Assert.assertEquals("There is 1 item in your cart.", itemInYourCart.getText());
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException ie){
        }
        action.moveToElement(closePopup).click().build().perform();

        WebElement productInCart = driver.findElement(By.xpath("//div[@class='shopping_cart']"));
        Assert.assertEquals("Cart 1 Product", productInCart.getText());

        //delete the product from the cart
        WebElement cart = driver.findElement(By.xpath("//div[@class='shopping_cart']/a"));
        action.moveToElement(cart).click().build().perform();

        WebElement deleteFromCart = driver.findElement(By.xpath("//i[@class='icon-trash']"));
        action.moveToElement(deleteFromCart).click().build().perform();

        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException ie){
        }

        //check if the cart is empty
        WebElement emptyCart = driver.findElement(By.xpath("//div[@class='shopping_cart']"));
        Assert.assertEquals("Cart (empty)", emptyCart.getText());

        WebElement alert = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
        Assert.assertEquals("Your shopping cart is empty.", alert.getText());

        driver.close();
    }
}