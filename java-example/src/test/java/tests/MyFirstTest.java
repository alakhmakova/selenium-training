package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyFirstTest extends TestBase{


  @Test
  public void waitTest() {
    app.driver.navigate().to("https://software-testing.ru/");
    app.driver.findElement(By.xpath("//a[contains(@href, '/edu/')]")).click();
    assertTrue(app.isElementPresent(By.id("jsn-master")));
  }
  @Test
  public void myFirstTest(){
//    driver.get("https://software-testing.ru/");
//    wait.until(titleIs("Software-Testing.Ru"));
    app.driver.get("https://www.google.com/");
   app.driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]/div")).click();
    app.driver.findElement(By.name("q")).sendKeys("webdriver");
    app.driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[1]")).click();
    app.wait.until(ExpectedConditions.or(
            ExpectedConditions.titleIs("webdriver - Google Search"),
            ExpectedConditions.titleIs("webdriver - Поиск в Google"),
            ExpectedConditions.titleIs("webdriver - Google Zoeken")//заголовок страницы меняется, поэтому пришлось усложнить условие на "или"
    ));
  }
  @Test
  public void mySecondTest(){
    app.driver.navigate().to("https://software-testing.ru/");
    assertFalse(app.isElementPresent(By.name("XXX")));
    //assertFalse(isElementPresent(By.xpath("//div[")));
  }

  @Test
  public void myThirdTest(){
    app.driver.navigate().to("https://software-testing.ru/");
    assertFalse(app.areElementsPresent(By.name("XXX")));
    //assertFalse(areElementsPresent(By.xpath("//div[")));
  }

}
