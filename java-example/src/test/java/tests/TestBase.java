package tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import model.UserData;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
  public void start() {
    if (tlDriver.get() != null) {
      driver = tlDriver.get();
      wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      return;
    }
    //как установить соединение с Selenium Server
    /*DesiredCapabilities capabilities = new DesiredCapabilities ();
    capabilities.setBrowserName("chrome");//браузер для удаленного запуска менять здесь
    driver = new RemoteWebDriver (capabilities);*/
    driver = new InternetExplorerDriver();//браузер менять здесь
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    tlDriver.set(driver);
    System.out.println(((HasCapabilities) driver).getCapabilities());
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    Runtime.getRuntime().addShutdownHook(
            new Thread(() -> {driver.quit(); driver = null;}));
  }

  public void login(){
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }
  @After
  public void stop(){
//    driver.quit();
//    driver = null;
  }
}
