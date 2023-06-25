package tests;

import com.google.common.io.Files;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class TestBase {

  public static ThreadLocal<EventFiringWebDriver> tlDriver = new ThreadLocal<>();
  //public WebDriver driver;
  public EventFiringWebDriver driver;
  public WebDriverWait wait;
  public BrowserMobProxy proxy;


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

  public static class MyListener extends AbstractWebDriverEventListener{
    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      System.out.println(throwable);
      File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      File screen = new File("screen-" +System.currentTimeMillis() + ".png");
      try {
        Files.copy(tmp, screen);
      } catch (IOException e) {
        e.printStackTrace();//throw new RuntimeException(e);
      }
      System.out.println(screen);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      System.out.println(by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
      System.out.println(by + " found");
    }
  }
  @Before
  public void start() throws MalformedURLException {
    if (tlDriver.get() != null) {
      driver = tlDriver.get();
      wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      return;
    }

    // Установите URL-адрес удаленной машины, где запущен Selenium Server
    URL remoteUrl = new URL("http://localhost:4444/wd/hub");

    proxy = new BrowserMobProxyServer();
    proxy.start(0);
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
    ChromeOptions options = new ChromeOptions();
    options.setCapability(CapabilityType.PROXY, seleniumProxy);
    driver = new EventFiringWebDriver(new ChromeDriver(options));

    //как установить соединение с Selenium Server
   /**DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName("firefox");//браузер для удаленного запуска менять здесь: internet explorer, MicrosoftEdge
    capabilities.setPlatform(Platform.LINUX);
    driver = new EventFiringWebDriver(new RemoteWebDriver(remoteUrl, capabilities));**/

    //driver = new EventFiringWebDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions()));
    //driver = new EdgeDriver();//браузер менять здесь - заменить на EventFiringWebDriver

    /**DesiredCapabilities cap = DesiredCapabilities.chrome();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);**/

    driver.register(new MyListener());
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
