package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.EdgeDriver;




public class AdminLogin {
  private WebDriver driver;

  @Before
  public void start(){
    ChromeOptions options = new ChromeOptions();
    options.setBinary("C:/Users/buale/AppData/Local/BraveSoftware/Brave-Browser/Application/brave.exe");

    driver = new FirefoxDriver();
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
