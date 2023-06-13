package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AdminCheckout extends TestBase {
  @Test
  public void adminCheckoutTest() {
    //1) вход в панель администратора
    login();

    //2) 2 цикла прокликивают последовательно все пункты меню слева, включая вложенные пункты
    List<WebElement> menuItems = driver.findElements(By.id("app-"));//получаю список пунктов меню
    for (int i = 0; i < menuItems.size(); i++) {
      WebElement menuItem = menuItems.get(i);
      menuItem.click();//кликаю на каждый пункт из меню

      List<WebElement> insideMenuItems = driver.findElements(By.cssSelector("#app- > ul.docs > li"));//получаю список вложенных пунктов
      for (int j = 0; j < insideMenuItems.size(); j++) {
        WebElement insideMenuItem = insideMenuItems.get(j);
        insideMenuItem.click();//кликаю на каждый вложенных пункт

        //3) для каждой страницы вложенного пункта проверяю наличие заголовка
        assertTrue(isElementPresent(By.tagName("h1")));
//        WebElement header = driver.findElement(By.tagName("h1"));
//        System.out.println(header.getText());

        //приходится повторять поиск элементов, иначе получаю StaleElementReferenceException: stale element reference: stale element not found
        insideMenuItems = driver.findElements(By.cssSelector("#app- > ul.docs > li"));
      }

      //3) для каждой страницы, где нет вложенных пунктов проверяю наличие заголовка
      if (insideMenuItems.size() == 0) {
        assertTrue(isElementPresent(By.tagName("h1")));
//        WebElement menuHeader = driver.findElement(By.tagName("h1"));
//        System.out.println("1 " + menuHeader.getText());
      }

      //приходится повторять поиск элементов, иначе получаю StaleElementReferenceException: stale element reference: stale element not found
      menuItems = driver.findElements(By.id("app-"));
    }
  }
}
