package tests;

import appmanager.ApplicationManager;
import appmanager.MainPage;
import com.browserup.harreader.model.Har;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

public class BrowserLogsTest extends TestBase{
  ApplicationManager app = new ApplicationManager();
  @Test
  public void browserLogsTest() {
    app.mainPage().login();

    app.proxy.newHar();
    app.driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
    Har har = app.proxy.endHar();
    har.getLog().getEntries().forEach(l -> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));


    app.driver.findElement(By.linkText("[Root]")).click();
    app.driver.findElement(By.linkText("Rubber Ducks")).click();
    app.driver.findElement(By.linkText("Subcategory")).click();
    List<WebElement> products = app.driver.findElements(By.xpath("//a[contains(@href,'product_id=')][@title='Edit']"));

    for(int i = 0; i < products.size(); i++){
      app.driver.findElements(By.xpath("//a[contains(@href,'product_id=')][@title='Edit']")).get(i).click();

      String desiredLogLevel = "WARNING";
      LogEntries logs = app.driver.manage().logs().get(LogType.BROWSER);
      for (LogEntry log : logs) {
        if (log.getLevel().getName().equals(desiredLogLevel)) {
          System.out.println(log);
        }
      }

      app.driver.findElement(By.name("cancel")).click();
      app.driver.findElement(By.linkText("[Root]")).click();
      app.driver.findElement(By.linkText("Rubber Ducks")).click();
      app.driver.findElement(By.linkText("Subcategory")).click();
    }
  }

}
