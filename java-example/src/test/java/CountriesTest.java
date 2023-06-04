import org.junit.Test;

public class CountriesTest extends TestBase {
  @Test
  public void countriesTest() {

  /*
  [x] Задание 8. Проверить сортировку стран и геозон на странице стран
Сделайте сценарий для учебного приложения litecart, который на странице http://localhost/litecart/admin/?app=countries&doc=countries

а) проверяет, что страны расположены в алфавитном порядке

б) для тех стран, у которых количество зон отлично от нуля -- открывает страницу этой страны и там проверяет, что геозоны расположены в алфавитном порядке
   */
    login();
    driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");//открыть  админку на странице Countries

  }
}