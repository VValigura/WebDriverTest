import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class Day5 {
    private WebDriver driver;

    @Before
    public void start(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }



    //1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты (чтобы не конфликтовало с ранее созданными пользователями),
    //2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
    //3) повторный вход в только что созданную учётную запись,
    //4) и ещё раз выход.
    @Test
    public void userRegistration(){
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("#box-account-login a")).click();

        String userEmail = String.format("test%d@mail.com", (int)(Math.random()*100000));
        String userPass = "password";

        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys("firstname");
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys("lastname");
        driver.findElement(By.cssSelector("input[name=address1]")).sendKeys("address1");
        driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[name=city]")).sendKeys("city");
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(userEmail);
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("0631112233");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(userPass);
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(userPass);
        driver.findElement(By.cssSelector("[name=create_account]")).click();

        driver.findElement(By.linkText("Logout")).click();

        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(userEmail);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(userPass);
        driver.findElement(By.cssSelector("button[name=login]")).click();

        driver.findElement(By.linkText("Logout")).click();
    }


//    Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).
//    Для добавления товара нужно открыть меню Catalog, в правом верхнем углу нажать кнопку "Add New Product", заполнить поля с информацией о товаре и сохранить.
//    Достаточно заполнить только информацию на вкладках General, Information и Prices. Скидки (Campains) на вкладке Prices можно не добавлять.
//    После сохранения товара нужно убедиться, что он появился в каталоге (в админке). Клиентскую часть магазина можно не проверять.
//    Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
    @Test
    public void addProduct(){
        HelpMethods.loginAsAdmin(driver);
        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.linkText("Add New Product")).click();
        String productName = String.format("productId%d", (int)(Math.random()*100000));
        driver.findElement(By.cssSelector("input[name=\"name[en]\"]")).sendKeys(productName);
        driver.findElement(By.cssSelector("input[name=code]")).sendKeys(Integer.toString((int)(Math.random()*10000)));
        WebElement chekBox = driver.findElement(By.cssSelector("input[name=\"product_groups[]\"][value=\"1-2\"]"));
        if (!chekBox.isSelected()){
            chekBox.click();
        }

        driver.findElement(By.linkText("Information")).click();
        Select manufacturer = new Select(driver.findElement(By.cssSelector("select[name = manufacturer_id]")));
        manufacturer.selectByIndex(1);
        driver.findElement(By.cssSelector("button[name=save]")).click();
        assertTrue(driver.findElements(By.linkText(productName)).size() == 1);

    }


    @After
    public void stop(){
        driver.quit();
    }
}
