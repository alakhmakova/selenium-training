import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.Assert.*;

public class CorrectProductPageTest extends TestBase{
  @Test
  public void correctProductPageTest() {
//Открыть  litecart на главной странице
    driver.get("http://localhost/litecart/");

//Найти блок Campaigns
    WebElement сampaign = driver.findElement(By.xpath("//div[@id='box-campaigns']"));
    List<WebElement> products = сampaign.findElements(By.xpath(".//a[@class='link'][contains(@title,'Duck')]"));
    WebElement firstProduct = products.get(0);//выбрать первый товар в блоке Campaigns

//Главная страница
    String productNameHomePage = firstProduct.findElement(By.xpath("./div[@class='name']")).getText();//название продукта

//Обычная цена
    String regularPriceHomePage = firstProduct.findElement(By.xpath("//s[@class='regular-price']")).getText();
    String colorRegularPriceHomePage = firstProduct.findElement(By.xpath("//s[@class='regular-price']")).getCssValue("color");//цвет
    String lineThroughRegularPriceHomePage = firstProduct.findElement(By.xpath("//s[@class='regular-price']")).getCssValue("text-decoration");//зачеркнутая
    Dimension sizeRegularPriceHomePage = firstProduct.findElement(By.xpath("//s[@class='regular-price']")).getSize();
    int widthRegularPriceHomePage = sizeRegularPriceHomePage.getWidth();//ширина обычной цены
    int heightRegularPriceHomePage = sizeRegularPriceHomePage.getHeight();//высота обычной цены

//Акционная цена
    String сampaignPriceHomePage = firstProduct.findElement(By.xpath("//strong[@class='campaign-price']")).getText();
    String colorCampaignPriceHomePage = firstProduct.findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("color");//цвет
    String fontWeightCampaignPriceHomePage = firstProduct.findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("font-weight");//жирная
    Dimension sizeСampaignPriceHomePage = firstProduct.findElement(By.xpath("//strong[@class='campaign-price']")).getSize();
    int widthСampaignPriceHomePage = sizeСampaignPriceHomePage.getWidth();//ширина акционной цены
    int heightСampaignPriceHomePage = sizeСampaignPriceHomePage.getHeight();//высота акционной цены

//Открыть страницу продукта
    firstProduct.click();
    String productNameProductPage = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();//название продукта

//Обычная цена
    String regularPriceProductPage = driver.findElement(By.xpath("//s[@class='regular-price']")).getText();
    String colorRegularPriceProductPage = driver.findElement(By.xpath("//s[@class='regular-price']")).getCssValue("color");//цвет
    String lineThroughRegularProductPage = driver.findElement(By.xpath("//s[@class='regular-price']")).getCssValue("text-decoration");//зачеркнутая
    Dimension sizeRegularProductPage = driver.findElement(By.xpath("//s[@class='regular-price']")).getSize();
    int widthRegularPriceProductPage = sizeRegularProductPage.getWidth();//ширина обычной цены
    int heightRegularPriceProductPage = sizeRegularProductPage.getHeight();//высота обычной цены

//Акционная цена
    String сampaignPriceProductPage = driver.findElement(By.xpath("//strong[@class='campaign-price']")).getText();
    String colorCampaignPriceProductPage = driver.findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("color");//цвет
    String fontWeightCampaignPriceProductPage = driver.findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("font-weight");//жирная
    Dimension sizeСampaignPriceProductPage = driver.findElement(By.xpath("//strong[@class='campaign-price']")).getSize();
    int widthСampaignPriceProductPage = sizeСampaignPriceProductPage.getWidth();//ширина акционной цены
    int heightСampaignPriceProductPage = sizeСampaignPriceProductPage.getHeight();//высота акционной цены


//Тесты работают в разных браузерах (Chrome, Firefox, IE)

//а) на главной странице и на странице товара совпадает текст названия товара
    assertEquals(productNameHomePage, productNameProductPage);

//б) на главной странице и на странице товара совпадают цены (обычная)
    assertEquals(regularPriceHomePage, regularPriceProductPage);

//б) на главной странице и на странице товара совпадают цены (акционная)
    assertEquals(сampaignPriceHomePage, сampaignPriceProductPage);

//в) обычная цена зачёркнутая на главной странице
    assertTrue(lineThroughRegularPriceHomePage.matches(".*line-through.*"));
//в) обычная цена зачёркнутая на странице товара
    assertThat(lineThroughRegularProductPage, matchesPattern(".*line-through.*"));

//в) обычная цена серая (одинаковые значения для каналов R, G и B ) на главной странице
    assertTrue(colorRegularPriceHomePage.matches("^rgba\\((.*),\\s*\\1,\\s*\\1,.*\\)$") || colorRegularPriceHomePage.matches("^rgb\\((.*),\\s*\\1,\\s*\\1\\)$"));
//в) обычная цена серая (одинаковые значения для каналов R, G и B ) на страница товара
    //assertThat(colorRegularPriceProductPage, matchesPattern("^rgba\\((.*),\\s*\\1,\\s*\\1,.*\\)$"));//только chrome, IE
    assertTrue(colorRegularPriceProductPage.matches("^rgba\\((.*),\\s*\\1,\\s*\\1,.*\\)$") || colorRegularPriceProductPage.matches("^rgb\\((.*),\\s*\\1,\\s*\\1\\)$"));

//г) акционная цена жирная на главной странице
    //assertEquals("700", fontWeightCampaignPriceHomePage);// только Chrome
    assertTrue(fontWeightCampaignPriceHomePage.equals("700") || fontWeightCampaignPriceHomePage.equals("900"));
//г) акционная цена жирная на странице товара
    assertEquals("700", fontWeightCampaignPriceProductPage);

//г) акционная цена красная на главной странице
    assertTrue(colorCampaignPriceHomePage.matches("rgba\\(.*, 0, 0, .*\\)") || colorCampaignPriceHomePage.matches("rgb\\(.*, 0, 0\\)"));
//г) акционная цена красная на странице товара
    assertTrue(colorCampaignPriceProductPage.matches("rgba\\(.*, 0, 0, .*\\)") || colorCampaignPriceProductPage.matches("rgb\\(.*, 0, 0\\)"));

//д) акционная цена крупнее, чем обычная на главной странице
    assertTrue(widthСampaignPriceHomePage > widthRegularPriceHomePage && heightСampaignPriceHomePage > heightRegularPriceHomePage);
//д) акционная цена крупнее, чем обычная на странице товара
    assertTrue(widthСampaignPriceProductPage > widthRegularPriceProductPage && heightСampaignPriceProductPage > heightRegularPriceProductPage);

  }
}
