package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

public class MainPage {
  //public WebDriver driver;
  private EventFiringWebDriver driver;

  public MainPage(EventFiringWebDriver driver) {
    this.driver = driver;
  }

  //public MainPage main() {return mainPage;}
  public void openFirstProductOnTheMainPage () {
    driver.get("http://localhost/litecart/");/**1) открыть главную страницу**/
    List<WebElement> products = driver.findElements(By.className("image-wrapper"));//получить список всех товаров с главной страницы
    products.get(0).click();/**2) открыть первый товар из списка**/
  }
  public void openTheCart () {
    /**6) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)**/
    driver.findElement(By.linkText("Checkout »")).click();//главная страница, откуда выбирается товар - на самом деле это можно сделать с любой страницы
  }
}
