package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
  private EventFiringWebDriver driver;
  private WebDriverWait wait;
  public ProductPage(EventFiringWebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }
  public void actualQuantityAfterFirstProductWasAdded (String quantityExpectedAfter){
      WebElement quantityAfter = driver.findElement(By.className("quantity"));
      wait.until(ExpectedConditions.domPropertyToBe(quantityAfter, "textContent", quantityExpectedAfter));/**4) подождать, пока счётчик товаров в корзине обновится**/}

  public void addProductToTheCart () {
      driver.findElement(By.name("add_cart_product")).click();/**3) добавить товар в корзину**/}

  public boolean isElementPresent (By locator){
    try {
      wait.until((WebDriver d) -> d.findElement(locator));
      //driver.findElement(locator);
      return true;
    } catch (TimeoutException ex) {//NoSuchElementException или TimeoutException
      return false;
    }
  }

  public void selectSizeForProduct () {
      if (isElementPresent(By.name("options[Size]"))) {//когда есть селект для выбора размера, он блокирует добавление в корзину, если размер не выбран
        WebElement size = driver.findElement(By.name("options[Size]"));
        Select select1 = new Select(size);
        select1.selectByIndex(1);
      }
    }

  public String expectedQuantityAfterFirstProductWasAdded () {
      int quantityBefore = Integer.parseInt(driver.findElement(By.className("quantity")).getAttribute("textContent"));
      String quantityExpectedAfter = String.valueOf(quantityBefore + 1);//ожидаемое количество товаров на счетчике после добавления товара
      return quantityExpectedAfter;
    }
}
