import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class Day4 {
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

        login(driver);
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countries = driver.findElements(By.cssSelector(".dataTable tr.row"));
        ArrayList<String> countriesName = new ArrayList<>();
        ArrayList<String> countriesZonesLink = new ArrayList<>();

        for (WebElement country:
             countries) {
            countriesName.add(country.findElement(By.cssSelector("a")).getText());
            if (Integer.parseInt(country.findElement(By.cssSelector("td:nth-child(6)")).getText()) > 0){
                countriesZonesLink.add(country.findElement(By.cssSelector("a")).getAttribute("href"));
            }
        }
        assertTrue(isSorted(countriesName));

        for (String countryZones:
                countriesZonesLink) {
            ArrayList<String> zoneName = new ArrayList<>();
            driver.get(countryZones);

            List<WebElement> tmp = driver.findElements(By.cssSelector(".dataTable td:nth-child(3) input[type=\"hidden\"]"));

            for (WebElement tmpZone:
                    tmp) {
                zoneName.add(tmpZone.getText());
            }
            isSorted(zoneName);
        }

    }


    @Test
    public void test2(){
        login(driver);
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        ArrayList<String> countriesName = new ArrayList<>();
        for (WebElement element:
             driver.findElements(By.cssSelector(".dataTable td:nth-child(3) a"))) {
            countriesName.add(element.getText());
        }
        assertTrue(isSorted(countriesName));
    }

    @Test
    public void test3(){
        driver.get("http://localhost/litecart/");
        WebElement firstProduct = driver.findElements(By.cssSelector(".product")).get(0);
        String name = firstProduct.findElement(By.cssSelector(".name")).getText();
        String regularPrice = driver.findElement(By.cssSelector(".product s")).getText();
        String campaignPrice = driver.findElement(By.cssSelector(".product strong")).getText();
        firstProduct.click();
        assertTrue(name.equals(driver.findElement(By.cssSelector("h1.title")).getText()));
        assertTrue(regularPrice.equals(driver.findElement(By.cssSelector(".information .price")).getText()));

    }




    public void login(WebDriver driver){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.cssSelector("input[name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name=\"login\"]")).click();
    }

    public boolean isSorted(List list){
        ArrayList<String> tmp = new ArrayList(list);
        Collections.sort(tmp);
        return tmp.equals(list);

    }

    @After
    public void stop(){
        driver.quit();
    }

}
