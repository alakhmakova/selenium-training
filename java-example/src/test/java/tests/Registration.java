package tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Registration extends TestBase {
  @Before
  public void ensurePreconditions() {
    // В форме регистрации есть капча, её нужно отключить в админке учебного приложения на вкладке Settings -> Security.
    login();
    WebElement settings = driver.findElement(By.xpath("//span[@class='name'][text()='Settings']"));
    settings.click();
    WebElement security = driver.findElement(By.xpath("//span[@class='name'][text()='Security']"));
    security.click();
    WebElement captcha = driver.findElement(By.xpath("//tr[@class='row'][.//td[text()='CAPTCHA']]"));
    WebElement edit = captcha.findElement(By.xpath("//a[@title='Edit'][contains(@href, 'key=captcha_enabled')]"));
    edit.click();
    WebElement captchaFalse = driver.findElement(By.xpath("//label[text()=' False']"));
    if (captchaFalse.isEnabled()) {
      captchaFalse.click();
    }
    WebElement save = driver.findElement(By.xpath("//button[@name='save']"));
    save.click();
  }


  @Test
  public void registrationTest() {
    /**
     Сценарий должен состоять из следующих частей:

     3) повторный вход в только что созданную учётную запись,
     4) и ещё раз выход.

     В качестве страны выбирайте United States, штат произвольный. При этом формат индекса - пять цифр.

     Проверки можно никакие не делать, только действия -- заполнение полей, нажатия на кнопки и ссылки.
     Если сценарий дошёл до конца, то есть созданный пользователь смог выполнить вход и выход -- значит создание прошло успешно.
     **/

    //открыть в litecart форму регистрации
    driver.get("http://localhost/litecart/");
    WebElement newCustomersButton = driver.findElement(By.xpath("//a[text()='New customers click here']"));
    newCustomersButton.click();
    WebElement country = driver.findElement(By.className("select2-selection__arrow"));
    country.click();
    WebElement search = driver.findElement(By.className("select2-search__field"));
    search.sendKeys("United States");
    WebElement us = driver.findElement(By.xpath("//li[text()='United States']"));
    us.click();
    long now = System.currentTimeMillis ();
    WebElement firstName = driver.findElement(By.name("firstname"));
    firstName.sendKeys("First");
    WebElement lastName = driver.findElement(By.name("lastname"));
    lastName.sendKeys("Last");
    WebElement address1 = driver.findElement(By.name("address1"));
    address1.sendKeys("address1");
    WebElement postcode = driver.findElement(By.name("postcode"));
    postcode.sendKeys("12345");
    WebElement city = driver.findElement(By.name("city"));
    city.sendKeys("12345");
    WebElement email = driver.findElement(By.name("email"));
    email.sendKeys(String.format ("user%s@localhost", now));
    WebElement phone = driver.findElement(By.name("phone"));
    phone.sendKeys("+18676445685978698");
    WebElement password = driver.findElement(By.name("password"));
    password.sendKeys("password");
    WebElement confirmedPassword = driver.findElement(By.name("confirmed_password"));
    confirmedPassword.sendKeys("password");
    WebElement submit = driver.findElement(By.cssSelector ("[name=create_account]"));
    submit.click();
WebElement logout = driver.findElement(By.xpath("//a[text()='Logout']"));
    logout.click();
//    regHelp.fillCreateAccountForm(new UserData().setFirstName("LastName").setLastName("FirstName").setAddress1 ("Address")
//            .setCity ("City%s").setPostcode ("12345").setEmail (String.format ("user%s@localhost", now))
//            .setPhone (String.format ("+1000675643")).setPassword ("password"));

  }
}
