package tests;

import org.junit.Test;
import org.openqa.selenium.By;

public class CartTests extends TestBase {

  @Test
  public void cartTest() {
    /**
     1) открыть главную страницу
     2) открыть первый товар из списка
     2) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
     3) подождать, пока счётчик товаров в корзине обновится
     4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
     5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
     6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
     **/

    /**5) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара - для этого применяю цикл**/
    for (int i = 0; i<3; i++ ) {
      app.mainPage().openFirstProductOnTheMainPage();//главная страница, откуда выбирается товар

      app.productPage().expectedQuantityAfterFirstProductWasAdded();//страница товара, откуда происходит добавление товара в корзину
      app.productPage().selectSizeForProduct();
      app.productPage().addProductToTheCart();
      app.productPage().actualQuantityAfterFirstProductWasAdded(app.productPage().expectedQuantityAfterFirstProductWasAdded());
    }

    app.mainPage().openTheCart();//открыть корзину можно с любой страницы, но я поместила это на главную страницу


/**7) удалить все товары из корзины один за другим**///страница корзины, откуда происходит удаление
    int iterations = app.driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr/td[@class='item']")).size();
    for (int i = 0; i < iterations; i++ ) {
      app.cartPage().removeOneProductAndCheckIt();
    }
  }

}
