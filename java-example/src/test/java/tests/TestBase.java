package tests;

import appmanager.ApplicationManager;
import com.google.common.io.Files;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestBase {
  protected final ApplicationManager app = new ApplicationManager();



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
  public void setUp() throws MalformedURLException {
    app.start();
  }


  //  @After
//  public void stop() {
//    driver.quit();
//    driver = null;
//  }
}
