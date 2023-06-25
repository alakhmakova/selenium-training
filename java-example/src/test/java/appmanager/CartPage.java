package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
  private EventFiringWebDriver driver;
  private WebDriverWait wait;

  public CartPage(EventFiringWebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  public boolean isElementPresent(By locator){
    try {
      wait.until((WebDriver d) -> d.findElement(locator));
      return true;
    } catch (TimeoutException ex) {
      return false;
    }
  }

  public void removeOneProductAndCheckIt () {
    int removeOneProduct = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr/td[@class='item']")).size() - 1;
    wait.until(ExpectedConditions.elementToBeClickable(By.name("remove_cart_item"))).click();
    if (isElementPresent(By.xpath("//h2[text()='Order Summary']"))) {//чтобы избежать падения теста, когда все товары будут удалены
      /**после каждого удаления подождать, пока внизу обновится таблица**/
      wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//table[@class='dataTable rounded-corners']//tr/td[@class='item']"), removeOneProduct));
    }
  }
}
