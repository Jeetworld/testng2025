//package testcases;
//
//import org.example.base.BaseClass;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.*;
//import org.testng.annotations.Test;
//
//import java.io.File;
//
//import static org.openqa.selenium.support.locators.RelativeLocator.with;
//
//
//public class Selenium4 extends BaseClass {
//
//
//
//
//    @Test
//    public void methodOne() throws Throwable{
//
//
//        WebDriver driver = getDriver();
//        WebElement element = driver.findElement(By.id("kamal"));
//
//        //get the dimentions of selenium 4
//        element.getRect().getDimension().getHeight();
//        element.getRect().getDimension().getWidth();
//        element.getRect().getX();
//        element.getRect().getY();
//
//        //switchTo().NewTab or NewWindow
//        driver.switchTo().newWindow(WindowType.TAB);
//        driver.switchTo().newWindow(WindowType.WINDOW);
//
//        //getScreenshot
//        File pic = element.getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(pic, pic);
//
//        // Reference element
//        WebElement referenceElement = driver.findElement(By.id("referenceElementId"));
//        WebElement elementAbove = driver.findElement(with(By.tagName("input")).above(referenceElement));
//
//    }
//}
