import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeoZonesTest extends TestBase{

  @Test
  public void geoZonesTest() {

    login();
    driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");//открыть  админку на странице GeoZones

    WebElement table = driver.findElement(By.className("dataTable"));//получить таблицу со страними
    List<WebElement> rows = table.findElements(By.className("row"));//получить все строки таблицы
    int size = rows.size();
    for (int i = 0; i < size; i++)  {
      WebElement row = rows.get(i);
      WebElement cellWithName = row.findElement(By.xpath(".//td[3]"));
      WebElement countryName = cellWithName.findElement(By.xpath("./a"));//найти ссылку по которой можно кликнуть
      countryName.click();//открыть страницу страны


      List<WebElement> zones = driver.findElements(By.xpath("//select[contains(@name, 'zones') and contains(@name, '[zone_code]')]"));//получить все меню с зонами со страницы
      List<String> zoneNames = new ArrayList<>();//создать пустой список с названиями зон
      for (WebElement zone : zones){
        WebElement selectedZone = zone.findElement(By.xpath("./option[@selected='selected']"));//получить выбранную зону для данного выпадающего меню
        String name = selectedZone.getText();
        zoneNames.add(name);//заполнить список выбранных зон конкретными названиями
      }


      List<String> sortedZones = new ArrayList<>(zoneNames);
      Collections.sort(sortedZones);
      List<String> sortedZonesResult = new ArrayList<>(sortedZones);//отсортировать полученные со страницы зоны по алфавиту и записать это в список

      assertEquals(sortedZonesResult, zoneNames);//проверить, что отсортированный список зон соответствует списку зон с сайта

      driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
      table = driver.findElement(By.className("dataTable"));
      rows = table.findElements(By.className("row"));
    }

  }
}
