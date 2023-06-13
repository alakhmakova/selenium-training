package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyFirstTest extends TestBase{


  @Test
  public void waitTest() {
    driver.navigate().to("https://software-testing.ru/");
    driver.findElement(By.xpath("//a[contains(@href, '/edu/')]")).click();
    assertTrue(isElementPresent(By.id("jsn-master")));
  }
  @Test
  public void myFirstTest(){
//    driver.get("https://software-testing.ru/");
//    wait.until(titleIs("Software-Testing.Ru"));
    driver.get("https://www.google.com/");
    driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]/div")).click();
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[1]")).click();
    wait.until(ExpectedConditions.or(
            ExpectedConditions.titleIs("webdriver - Google Search"),
            ExpectedConditions.titleIs("webdriver - Поиск в Google")//заголовок страницы меняется, поэтому пришлось усложнить условие на "или"
    ));
  }
  @Test
  public void mySecondTest(){
    driver.navigate().to("https://software-testing.ru/");
    assertFalse(isElementPresent(By.name("XXX")));
    //assertFalse(isElementPresent(By.xpath("//div[")));
  }

  @Test
  public void myThirdTest(){
    driver.navigate().to("https://software-testing.ru/");
    assertFalse(areElementsPresent(By.name("XXX")));
    //assertFalse(areElementsPresent(By.xpath("//div[")));
  }

}
