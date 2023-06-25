package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;


public class LinksInNewWindow extends TestBase {
  @Test
  public void linksInNewWindowTest() throws InterruptedException {
    /**зайти в админку**/
    app.login();
    String originalWindow = app.driver.getWindowHandle();// получаю идентификатор текущего окна

    app.driver.findElement(By.xpath("//span[text()='Countries']")).click();/**открыть пункт меню Countries**/

    /**открыть на редактирование какую-нибудь страну**/
    WebElement table = app.driver.findElement(By.className("dataTable"));//получить таблицу со страними
    List<WebElement> rows = table.findElements(By.xpath("//tr[@class='row']// i[@class='fa fa-pencil']"));//получить все карандашики
    rows.iterator().next().click();

    List<WebElement> links = app.driver.findElements(By.xpath("//i[@class='fa fa-external-link']"));
    for (WebElement link : links) {/**повторить действия для всех внешних ссылок при помощи цикла**/
      link.click();/**нажать на квадратик со стрелкой**/
//      Set<String> listOfWindows = driver.getWindowHandles();
//      System.out.println(listOfWindows);
      app.wait.until(numberOfWindowsToBe(2));/**ожидание открытия нового окна**/
      for (String newWindowHandle : app.driver.getWindowHandles()) {
        if (!originalWindow.contentEquals(newWindowHandle)) {//найти идентификатор нового окна
          app.driver.switchTo().window(newWindowHandle);/**переключится в новое окно**/
          break;
        }
      }
      app.driver.close();/**закрыть новое окно**/
      app.driver.switchTo().window(originalWindow);/**вернуться обратно в старое окно**/
    }
  }

}
