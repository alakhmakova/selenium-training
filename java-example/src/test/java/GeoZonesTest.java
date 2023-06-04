import org.junit.Test;

public class GeoZonesTest extends TestBase{

  @Test
  public void geoZonesTest() {

  /*
  [x] Задание 9. Проверить сортировку геозон на странице геозон
Сделайте сценарии, который на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones заходит в каждую из стран и проверяет, что зоны расположены в алфавитном порядке.
   */
    login();
    driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");//открыть  админку на странице GeoZones

  }
}
