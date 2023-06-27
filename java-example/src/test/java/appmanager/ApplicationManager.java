package appmanager;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.BrowserUpProxyServer;
import com.browserup.bup.client.ClientUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestBase;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {


  public EventFiringWebDriver driver;
  public WebDriverWait wait;
  public BrowserUpProxy proxy;
  public static ThreadLocal<EventFiringWebDriver> tlDriver = new ThreadLocal<>();


  private MainPage mainPage;
  private ProductPage productPage;
  private CartPage cartPage;

  public void start() throws MalformedURLException {
  if (tlDriver.get() != null) {
    driver = tlDriver.get();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    return;
  }

  // Установите URL-адрес удаленной машины, где запущен Selenium Server
  URL remoteUrl = new URL("http://localhost:4444/wd/hub");

   proxy = new BrowserUpProxyServer();
   proxy.start(0);
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
   ChromeOptions options = new ChromeOptions();
    options.setProxy(seleniumProxy);
   //options.setCapability(CapabilityType.PROXY, seleniumProxy);

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

  driver.register(new TestBase.MyListener());
  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  tlDriver.set(driver);
  System.out.println(((HasCapabilities) driver).getCapabilities());
  wait = new WebDriverWait(driver, Duration.ofSeconds(10));

  Runtime.getRuntime().addShutdownHook(
          new Thread(() -> {
            driver.quit();
            driver = null;
          }));
  mainPage = new MainPage(driver, proxy);
  productPage = new ProductPage(driver, wait);
  cartPage = new CartPage(driver, wait);
}
  public boolean isElementPresent (By locator){
    try {
      wait.until((WebDriver d) -> d.findElement(locator));
      //driver.findElement(locator);
      return true;
    } catch (TimeoutException ex) {//NoSuchElementException или TimeoutException
      return false;
    }
  }
  public boolean areElementsPresent (By locator){
      return driver.findElements(locator).size() > 0;
    }

//  public void login () {
//      driver.get("http://localhost/litecart/admin/");
//      driver.findElement(By.name("username")).sendKeys("admin");
//      driver.findElement(By.name("password")).sendKeys("admin");
//      driver.findElement(By.name("login")).click();
//    }

  public MainPage mainPage() {return mainPage;}

  public ProductPage productPage() {return productPage;}

  public CartPage cartPage() {return cartPage;}
}
