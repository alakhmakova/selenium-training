import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.MessageFormat;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CorrectProductPageTest extends TestBase{
  @Test
  public void correctProductPageTest() {

  /*
  [x] Задание 10. Проверить, что открывается правильная страница товара
Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница товара в учебном приложении litecart.

Более точно, нужно открыть главную страницу, выбрать первый товар в блоке Campaigns и проверить следующее:

а) на главной странице и на странице товара совпадает текст названия товара
б) на главной странице и на странице товара совпадают цены (обычная и акционная)
в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
(цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)

Необходимо убедиться, что тесты работают в разных браузерах, желательно проверить во всех трёх ключевых браузерах (Chrome, Firefox, IE).
   */
    driver.get("http://localhost/litecart/");//открыть  litecart на главной странице
    WebElement сampaign = driver.findElement(By.xpath("//div[@id='box-campaigns']"));//найти блок Campaigns box-most-popular, box-campaigns, box-latest-products
    List<WebElement> products = сampaign.findElements(By.xpath(".//a[@class='link'][contains(@title,'Duck')]"));//выбрать первый товар в блоке Campaigns
    WebElement firstProduct = products.get(0);

    String productNameHomePage = firstProduct.findElement(By.xpath("./div[@class='name']")).getText();
    String regularPriceHomePage = firstProduct.findElement(By.xpath("//s[@class='regular-price']")).getText();
    String сampaignPriceHomePage = firstProduct.findElement(By.xpath("//strong[@class='campaign-price']")).getText();
    String colourCampaignPriceHomePage = driver.findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("color");


    firstProduct.click();//открыть страницу продукта

    String productNameProductPage = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
    String regularPriceProductPage = driver.findElement(By.xpath("//s[@class='regular-price']")).getText();
    String сampaignPriceProductPage = driver.findElement(By.xpath("//strong[@class='campaign-price']")).getText();
    String colourCampaignPriceProductPage = driver.findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("color");
    System.out.println("colour of campaign price from product page " + colourCampaignPriceProductPage);

    assertEquals(productNameHomePage, productNameProductPage);
    assertEquals(regularPriceHomePage, regularPriceProductPage);
    assertEquals(сampaignPriceHomePage, сampaignPriceProductPage);
    assertEquals(MessageFormat.format("rgba({}, 0, 0, {})", ), colourCampaignPriceHomePage);


  }
}
