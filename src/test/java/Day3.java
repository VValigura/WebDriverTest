import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Day3 {
    private WebDriver driver;

    @Before
    public void start(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void test1(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.cssSelector("input[name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name=\"login\"]")).click();

        driver.findElement(By.id("app-")).click();
        assertTrue(driver.findElements(By.cssSelector("title")).size() > 0);
        driver.findElement(By.id("doc-template")).click();
        assertTrue(driver.findElements(By.cssSelector("title")).size() > 0);
        driver.findElement(By.id("doc-logotype")).click();
        assertTrue(driver.findElements(By.cssSelector("title")).size() > 0);
    }

    @Test
    public void test2(){
        driver.get("http://localhost/litecart");
        List<WebElement> products = driver.findElements(By.cssSelector(".product "));
        for (WebElement product: products) {
            assertEquals(product.findElements(By.cssSelector(".sticker")).size(), 1);
        }

    }


    @After
    public void stop(){
        driver.quit();
    }

}
