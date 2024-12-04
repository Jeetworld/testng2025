package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class JSFunctions {

    @Test
    public void js(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        WebElement element = driver.findElement(By.xpath("//*'@test='sss']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].value='VALUE';", element);	//enter the value in inputbox

        jse.executeScript("arguments[0].click();", element);	//click the button


        driver.quit();

    }
}
