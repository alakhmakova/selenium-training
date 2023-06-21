package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CartTests extends TestBase {

  @Test
  public void cartTest() {
    /**
     1) открыть главную страницу
     2) открыть первый товар из списка
     2) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
     3) подождать, пока счётчик товаров в корзине обновится
     4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
     5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
     6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
     **/

    /**5) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара - для этого применяю цикл**/
    for (int i = 0; i<3; i++ ) {
      driver.navigate().to("http://localhost/litecart/");/**1) открыть главную страницу**/
      List<WebElement> products = driver.findElements(By.className("image-wrapper"));//получить список всех товаров с главной страницы
      products.get(0).click();/**2) открыть первый товар из списка**/
      int quantityBefore = Integer.parseInt(driver.findElement(By.className("quantity")).getAttribute("textContent"));
      String quantityExpectedAfter = String.valueOf(quantityBefore + 1);//ожидаемое количество товаров на счетчике после добавления товара
      if(isElementPresent(By.name("options[Size]"))){//когда есть селект для выбора размера, он блокирует добавление в корзину, если размер не выбран
        WebElement size = driver.findElement(By.name("options[Size]"));
        Select select1 = new Select(size);
        select1.selectByIndex(1);
      }
      driver.findElement(By.name("add_cart_product")).click();/**3) добавить товар в корзину**/
      WebElement quantityAfter = driver.findElement(By.className("quantity"));
      wait.until(ExpectedConditions.domPropertyToBe(quantityAfter, "textContent", quantityExpectedAfter));/**4) подождать, пока счётчик товаров в корзине обновится**/
    }

    /**6) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)**/
    driver.findElement(By.linkText("Checkout »")).click();


/**7) удалить все товары из корзины один за другим**/
    int iterations = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr/td[@class='item']")).size();
    for (int i = 0; i < iterations; i++ ) {
      int removeOneProduct = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr/td[@class='item']")).size()- 1;
      wait.until(ExpectedConditions.elementToBeClickable(By.name("remove_cart_item"))).click();
      if(isElementPresent(By.xpath("//h2[text()='Order Summary']"))) {//чтобы избежать падения теста, когда все товары будут удалены
        /**после каждого удаления подождать, пока внизу обновится таблица**/
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//table[@class='dataTable rounded-corners']//tr/td[@class='item']"), removeOneProduct));
      }
    }
  }
}
