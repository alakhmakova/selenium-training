package appmanager;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.harreader.model.Har;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

public class MainPage {
  //public WebDriver driver;
  private EventFiringWebDriver driver;
  private BrowserUpProxy proxy;

  public MainPage(EventFiringWebDriver driver, BrowserUpProxy proxy) {
    this.driver = driver;
    this.proxy = proxy;
  }

  //public MainPage main() {return mainPage;}
  public void openFirstProductOnTheMainPage () {
    proxy.newHar();
    driver.get("http://192.168.0.106/litecart/");/**1) открыть главную страницу**/
    Har har = proxy.endHar();
    har.getLog().getEntries().forEach(l -> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));

    List<WebElement> products = driver.findElements(By.className("image-wrapper"));//получить список всех товаров с главной страницы
    products.get(0).click();/**2) открыть первый товар из списка**/
  }
  public void openTheCart () {
    /**6) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)**/

    driver.findElement(By.linkText("Checkout »")).click();//главная страница, откуда выбирается товар - на самом деле это можно сделать с любой страницы

  }
  public void login () {
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }
}
