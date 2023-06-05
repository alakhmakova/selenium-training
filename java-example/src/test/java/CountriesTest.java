import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CountriesTest extends TestBase {
  @Test
  public void countriesTest() {

    login();
    driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");//открыть  админку на странице Countries

    //а) проверить, что страны расположены в алфавитном порядке
    WebElement table = driver.findElement(By.className("dataTable"));//получить таблицу со страними
    List<WebElement> rows = table.findElements(By.className("row"));//получить все строки таблицы
    List<String> countryNames = new ArrayList<>();//создать пустой список с названиями стран
    for (WebElement row : rows) {
      WebElement cellWithName = row.findElement(By.xpath(".//td[5]"));
      String countryName = cellWithName.getText();//получить название каждой страны
      countryNames.add(countryName);//заполнить список стран конкретными названиями
    }

    List<String> sortedCountries = new ArrayList<>(countryNames);
    Collections.sort(sortedCountries);
    List<String> sortedCountriesResult = new ArrayList<>(sortedCountries);//отсортировать полученные с сайта страны по алфавиту и записать это в список

    assertEquals(sortedCountriesResult, countryNames);//проверить, что отсортированный список стран соответствует списку стран с сайта

    //б) для тех стран, у которых количество зон отлично от нуля - открыть страницу этой страны и там проверить, что геозоны расположены в алфавитном порядке

    List<WebElement> notZeroZoneCountries = driver.findElements(By.xpath("//tr[@class='row'][.//td[6][text() != '0']]"));//найти строки таблицы со странами, где количество зон отлично от нуля

    int size = notZeroZoneCountries.size();
    for (int i = 0; i < size; i++) {
      WebElement notZeroZoneCountry = driver.findElements(By.xpath("//tr[@class='row'][.//td[6][text() != '0']]")).get(i);//взять из списка 1 страну, где количество зон отлично от нуля
      WebElement countryRow = notZeroZoneCountry.findElement(By.xpath("./td[5]"));//найти у этой страны ячейку с именем страны
      WebElement countryName = countryRow.findElement(By.xpath("./a"));//найти ссылку по которой можно кликнуть
      countryName.click();//открыть страницу страны, где количество зон отлично от нуля


      List<WebElement> zones = driver.findElements(By.xpath("//input[contains(@name, 'zones') and contains(@name, '[name]')]"));//получить все зоны со страницы страны
      List<String> zoneNames = new ArrayList<>();//создать пустой список с названиями зон
      for (WebElement zone : zones){
        String name = zone.getAttribute("value");//получить название каждой зоны
        zoneNames.add(name);//заполнить список зон конкретными названиями
      }


      List<String> sortedZones = new ArrayList<>(zoneNames);
      Collections.sort(sortedZones);
      List<String> sortedZonesResult = new ArrayList<>(sortedZones);//отсортировать полученные со страницы зоны по алфавиту и записать это в список

      assertEquals(sortedZonesResult, zoneNames);//проверить, что отсортированный список зон соответствует списку зон с сайта

      driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");//со страницы конкретной страны вернуться к списку всех стран, чтобы повторить цикл
    }

  }
}