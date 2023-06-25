package tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Registration extends TestBase {
  @Before
  public void ensurePreconditions() {
    /**отключить капча в админке**/
    app.login();
    app.driver.findElement(By.xpath("//span[@class='name'][text()='Settings']")).click();
    app.driver.findElement(By.xpath("//span[@class='name'][text()='Security']")).click();
    WebElement captcha = app.driver.findElement(By.xpath("//tr[@class='row'][.//td[text()='CAPTCHA']]"));
    captcha.findElement(By.xpath("//a[@title='Edit'][contains(@href, 'key=captcha_enabled')]")).click();
    WebElement captchaFalse = app.driver.findElement(By.xpath("//label[text()=' False']"));
    if (captchaFalse.isEnabled()) {
      captchaFalse.click();
    }
    app.driver.findElement(By.xpath("//button[@name='save']")).click();
  }
  @Test
  public void registrationTest() {

    /**открыть в litecart форму регистрации**/
    app.driver.get("http://localhost/litecart/");
    app.driver.findElement(By.xpath("//a[text()='New customers click here']")).click();
    //в качестве страны выбрать United States
    app.driver.findElement(By.className("select2-selection__arrow")).click();
    app.driver.findElement(By.className("select2-search__field")).sendKeys("United States");
    app.driver.findElement(By.xpath("//li[text()='United States']")).click();

    /**регистрация новой учётной записи**/
    app.driver.findElement(By.name("firstname")).sendKeys("First");
    app.driver.findElement(By.name("lastname")).sendKeys("Last");
    app.driver.findElement(By.name("address1")).sendKeys("address1");
    app.driver.findElement(By.name("postcode")).sendKeys("12345");
    app.driver.findElement(By.name("city")).sendKeys("12345");
    //уникальный адрес электронной почты
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost", now);
    app.driver.findElement(By.name("email")).sendKeys(email);
    app.driver.findElement(By.name("phone")).sendKeys("+18676445685978698");
    String password = "password";
    app.driver.findElement(By.name("password")).sendKeys(password);
    app.driver.findElement(By.name("confirmed_password")).sendKeys("password");
    app.driver.findElement(By.cssSelector("[name=create_account]")).click();

    /**выход из аккаунта**/
    app.driver.findElement(By.xpath("//a[text()='Logout']")).click();

    /**повторный вход в только что созданную учётную запись**/
    app.driver.findElement(By.name("email")).sendKeys(email);
    app.driver.findElement(By.name("password")).sendKeys(password);
    app.driver.findElement(By.name("login")).click();

    /**ещё раз выход**/
    app.driver.findElement(By.xpath("//a[text()='Logout']")).click();

  }
}
