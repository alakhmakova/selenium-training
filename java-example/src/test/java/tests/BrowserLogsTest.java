package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

public class BrowserLogsTest extends TestBase{
  @Test
  public void browserLogsTest() {
    login();
    driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

    driver.findElement(By.linkText("[Root]")).click();
    driver.findElement(By.linkText("Rubber Ducks")).click();
    driver.findElement(By.linkText("Subcategory")).click();
    List<WebElement> products = driver.findElements(By.xpath("//a[contains(@href,'product_id=')][@title='Edit']"));

    for(int i = 0; i < products.size(); i++){
      driver.findElements(By.xpath("//a[contains(@href,'product_id=')][@title='Edit']")).get(i).click();

      String desiredLogLevel = "WARNING";
      LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
      for (LogEntry log : logs) {
        if (log.getLevel().getName().equals(desiredLogLevel)) {
          System.out.println(log);
        }
      }

      driver.findElement(By.name("cancel")).click();
      driver.findElement(By.linkText("[Root]")).click();
      driver.findElement(By.linkText("Rubber Ducks")).click();
      driver.findElement(By.linkText("Subcategory")).click();
    }
  }
}
