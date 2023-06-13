package tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Registration extends TestBase {
  @Before
  public void ensurePreconditions() {
    /**отключить капча в админке**/
    login();
    driver.findElement(By.xpath("//span[@class='name'][text()='Settings']")).click();
    driver.findElement(By.xpath("//span[@class='name'][text()='Security']")).click();
    WebElement captcha = driver.findElement(By.xpath("//tr[@class='row'][.//td[text()='CAPTCHA']]"));
    captcha.findElement(By.xpath("//a[@title='Edit'][contains(@href, 'key=captcha_enabled')]")).click();
    WebElement captchaFalse = driver.findElement(By.xpath("//label[text()=' False']"));
    if (captchaFalse.isEnabled()) {
      captchaFalse.click();
    }
    driver.findElement(By.xpath("//button[@name='save']")).click();
  }
  @Test
  public void registrationTest() {

    /**открыть в litecart форму регистрации**/
    driver.get("http://localhost/litecart/");
    driver.findElement(By.xpath("//a[text()='New customers click here']")).click();
    //в качестве страны выбрать United States
    driver.findElement(By.className("select2-selection__arrow")).click();
    driver.findElement(By.className("select2-search__field")).sendKeys("United States");
    driver.findElement(By.xpath("//li[text()='United States']")).click();

    /**регистрация новой учётной записи**/
    driver.findElement(By.name("firstname")).sendKeys("First");
    driver.findElement(By.name("lastname")).sendKeys("Last");
    driver.findElement(By.name("address1")).sendKeys("address1");
    driver.findElement(By.name("postcode")).sendKeys("12345");
    driver.findElement(By.name("city")).sendKeys("12345");
    //уникальный адрес электронной почты
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost", now);
    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("phone")).sendKeys("+18676445685978698");
    String password = "password";
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("confirmed_password")).sendKeys("password");
    driver.findElement(By.cssSelector("[name=create_account]")).click();

    /**выход из аккаунта**/
    driver.findElement(By.xpath("//a[text()='Logout']")).click();

    /**повторный вход в только что созданную учётную запись**/
    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("login")).click();

    /**ещё раз выход**/
    driver.findElement(By.xpath("//a[text()='Logout']")).click();

  }
}
