import org.asynchttpclient.util.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminLogin {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start(){
    driver = new ChromeDriver();
  }
  @Test
  public void adminLoginTest(){
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();

    String adminPage = "http://localhost/litecart/admin/";
    Assert.assertEquals(adminPage, driver.getCurrentUrl());
  }
  @After
  public void stop(){
    driver.quit();
    driver = null;
  }
}
