package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertTrue;

public class AddNewProduct extends TestBase {
  @Test
  public void addNewProductTest() throws InterruptedException {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    long now = currentTimeMillis();
    String name = "Set of Ducks " + now;


    /**В админке открыть меню Catalog, в правом верхнем углу нажать кнопку "Add New Product"**/
    login();
    driver.findElement(By.xpath("//span[@class='name'][text()='Catalog']")).click();
    driver.findElement(By.linkText("Add New Product")).click();

    /**заполнить информацию на вкладке General**/
    driver.findElement(By.xpath("//label[text()=' Enabled']")).click();
    driver.findElement(By.name("name[en]")).sendKeys(name);
    //driver.findElement(By.name("name[en]")).sendKeys(name);
    driver.findElement(By.name("code")).sendKeys(name);

    //Categories - хочу установить чек бокс только на Rubber Ducks и убедиться, что остальные чек-боксы не выбраны
    WebElement categories = driver.findElement(By.xpath("//input[@name='categories[]'][@data-name='Root']"));
    if (!categories.isSelected()) {
      categories.click();
    }
    WebElement rubberDucks = driver.findElement(By.xpath("//input[@name='categories[]'][@data-name='Rubber Ducks']"));
    if (!categories.isSelected()) {
      rubberDucks.click();
    }
    WebElement subcategory = driver.findElement(By.xpath("//input[@name='categories[]'][@data-name='Subcategory']"));
    if (subcategory.isSelected()) {
      subcategory.click();
    }
    //driver.findElement(By.xpath("//input[@name='categories[]'][@data-name='Rubber Ducks']")).click();

    //Product Groups - хочу установить чек бокс только на Unisex и убедиться, что остальные чек-боксы не выбраны
    WebElement female = driver.findElement(By.xpath("//input[@value='1-2']"));
    if (female.isSelected()) {
      female.click();
    }
    WebElement male = driver.findElement(By.xpath("//input[@value='1-1']"));
    if (male.isSelected()) {
      male.click();
    }
    WebElement unisex = driver.findElement(By.xpath("//input[@value='1-3']"));
    if (!unisex.isSelected()) {
      unisex.click();
    }
    //driver.findElement(By.xpath("//input[@value='1-3']")).click();

    //Добавить количество
    WebElement quantity = driver.findElement(By.name("quantity"));
    quantity.clear();
    quantity.sendKeys("5");

    //Определить наличие
    WebElement dropdownSoldOutStatus = driver.findElement(By.name("sold_out_status_id")); // Найти выпадающийо список
    Select select = new Select(dropdownSoldOutStatus); // Создать экземпляр класса Select
    String text = "Sold out"; // Значение, которое нужно выбрать
    select.selectByVisibleText(text); // Выбор элемента по видимому тексту

    //Загрузить фото
    /**
     Картинку с изображением товара нужно уложить в репозиторий вместе с кодом.
     При этом указывать в коде полный абсолютный путь к файлу плохо, на другой машине работать не будет.
     Надо средствами языка программирования преобразовать относительный путь в абсолютный.
     **/
    File photo = new File("src/test/resources/set.jpg");
    WebElement foto = driver.findElement(By.xpath("//input[@name='new_images[]']"));
    foto.sendKeys(photo.getAbsolutePath());

    //Календарь
    jsExecutor.executeScript("document.getElementsByName('date_valid_from')[0].value = '2023-06-10';");
    jsExecutor.executeScript("document.getElementsByName('date_valid_to')[0].value = '2025-12-01';");

    /**заполнить информацию на вкладке Information**/
    driver.findElement(By.linkText("Information")).click();

    //Selects
    WebElement manufacturer = driver.findElement(By.name("manufacturer_id")); // Найти выпадающийо список
    Select select1 = new Select(manufacturer); // Создать экземпляр класса Select
    String value = "1"; // Значение, которое нужно выбрать
    select1.selectByValue(value); // Выбор элемента по value

    WebElement supplier = driver.findElement(By.name("supplier_id")); // Найти выпадающийо список
    Select select2 = new Select(supplier); // Создать экземпляр класса Select
    select2.selectByIndex(0); // Выбор элемента по порядковому номеру

    //Описание
    String keywords = "test0 test1 test2";
    String shortDescription = "Electric duck toy funny duck learning toy " +
            "Electric duck toy funny duck learning toy Funny chinese language duck learning toy for kids with window box. Shantou Chenghai professional toys supplier.";
    String editorText = "Description ";


    driver.findElement(By.name("keywords")).sendKeys(keywords);
    WebElement shortDescript = driver.findElement(By.name("short_description[en]"));
    shortDescript.sendKeys(shortDescription);

    //Быстрое копирование и вставка
    shortDescript.sendKeys(Keys.chord(Keys.CONTROL, "A"));
    shortDescript.sendKeys(Keys.chord(Keys.CONTROL, "C"));
    WebElement editor = driver.findElement(By.className("trumbowyg-editor"));
    editor.sendKeys(Keys.chord(Keys.CONTROL, "V"));
    //Вставка текста в середину
    editor.sendKeys(Keys.HOME);
    editor.sendKeys(Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT);
    editor.sendKeys(Keys.ENTER);
    editor.sendKeys(editorText);
    editor.sendKeys(Keys.ENTER);
    //Перемещение по полям
    editor.sendKeys(Keys.TAB, Keys.TAB);


    /**заполнить информацию на вкладке Prices**/
    driver.findElement(By.linkText("Prices")).click();

    jsExecutor.executeScript("document.getElementsByName('purchase_price')[0].value = '7.00';");

    WebElement purchasePrice = driver.findElement(By.name("purchase_price_currency_code"));
    Select select3 = new Select(purchasePrice);
    String currencyValue = "USD";
    select3.selectByValue(currencyValue);

    WebElement taxClass = driver.findElement(By.name("tax_class_id"));
    Select select4 = new Select(taxClass);
    select4.selectByIndex(0);

    jsExecutor.executeScript("document.getElementsByName('prices[USD]')[0].value = '50';");
    jsExecutor.executeScript("document.getElementsByName('gross_prices[EUR]')[0].value = '7.50';");

//    WebElement pricesUSD = driver.findElement(By.name("prices[USD]"));
//    pricesUSD.click();
//    pricesUSD.clear();
//    pricesUSD.sendKeys("20.00");
//
//    WebElement grossEUR = driver.findElement(By.name("gross_prices[EUR]"));
//    grossEUR.click();
//    grossEUR.clear();
//    grossEUR.sendKeys("7.50");


/**Сохранить**/
    driver.findElement(By.name("save")).click();

    /**После сохранения товара нужно убедиться, что он появился в каталоге в админке**/
    driver.findElement(By.linkText("Rubber Ducks")).click();//на всякий случай захожу в нужную категорию, куда сохраняла товар
    assertTrue(isElementPresent(By.linkText(name)));

  }


}
