import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Stickers extends TestBase {
  @Test
  public void stickersTest() {
    driver.navigate().to("http://localhost/litecart/");//открыть  litecart на главной странице
    List<WebElement> products = driver.findElements(By.className("image-wrapper"));//получить список всех товаров с главной страницы
    for(WebElement product : products){
      List<WebElement> stickers = product.findElements(By.xpath("./div[contains(@class,'sticker')]"));//на каждом товаре найти стикеры, неважно, что на них написано
      assertEquals(1, stickers.size());//проверить, что у каждого товара имеется ровно один стикер
    }
  }
}