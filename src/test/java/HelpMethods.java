import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelpMethods {

    public static void loginAsAdmin(WebDriver driver){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.cssSelector("input[name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name=\"login\"]")).click();
    }
}
