package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestBase {

  public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
  public WebDriver driver;
  public WebDriverWait wait;


  public boolean isElementPresent(By locator) {
    try {
      wait.until((WebDriver d) -> d.findElement(locator));
      //driver.findElement(locator);
      return true;
    } catch (TimeoutException ex) {//NoSuchElementException или TimeoutException
      return false;
    }
  }

  public boolean areElementsPresent(By locator) {
    return driver.findElements(locator).size() > 0;
  }

  @Before
  public void start() throws MalformedURLException {
    if (tlDriver.get() != null) {
      driver = tlDriver.get();
      wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      return;
    }

    // Установите URL-адрес удаленной машины, где запущен Selenium Server
    URL remoteUrl = new URL("http://192.168.229.128:4444/wd/hub");

    //как установить соединение с Selenium Server
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName("firefox");//браузер для удаленного запуска менять здесь
    driver = new RemoteWebDriver(remoteUrl, capabilities);

    // driver = new EdgeDriver();//браузер менять здесь
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    tlDriver.set(driver);
    System.out.println(((HasCapabilities) driver).getCapabilities());
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    Runtime.getRuntime().addShutdownHook(
            new Thread(() -> {
              driver.quit();
              driver = null;
            }));
  }

  public void login() {
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

//  @After
//  public void stop() {
//    driver.quit();
//    driver = null;
//  }
}
