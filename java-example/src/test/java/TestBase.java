import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestBase {

  public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

  public WebDriver driver;
  public WebDriverWait wait;


  public boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

  public boolean areElementsPresent(By locator) {
    return driver.findElements(locator).size() > 0;
  }

  @Before
  public void start() {
    if (tlDriver.get() != null) {
      driver = tlDriver.get();
      wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      return;
    }
    driver = new ChromeDriver();
    tlDriver.set(driver);
    System.out.println(((HasCapabilities) driver).getCapabilities());
  }
  @After
//  public void tearDown() {
//    if (driver != null) {
//      driver.quit();
//    }
//  }
  public void stop(){
    driver.quit();
    driver = null;
  }
}
