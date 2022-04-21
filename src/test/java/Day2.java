import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;

public class Day2 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        // System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    public void test(){
        driver.navigate().to("https://www.google.com.ua/");
        driver.findElement(By.name("q")).sendKeys("WebDriver");
    }

    @Test
    public void test2(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("unexpectedAlertBehaviour", "dismiss");
        WebDriver driver = new ChromeDriver(caps);
        System.out.println(((HasCapabilities) driver).getCapabilities());


    }



    @After
    public void stop(){
        driver.quit();
    }






}
