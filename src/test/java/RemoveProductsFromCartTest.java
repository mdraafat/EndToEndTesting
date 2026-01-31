import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CartPage;
import page.HomePage;
import util.framework.Framework;


@Feature("Cart Management Tests")
public class RemoveProductsFromCartTest {

    private HomePage homePage;

    private CartPage cartPage;
    private Framework framework;

    @BeforeMethod
    public void setup() {
        framework = Framework.start();
        homePage = new HomePage(framework);
        cartPage = new CartPage(framework);
    }

    @Test
    public void removeProductsFromCart() {

        homePage.goToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        homePage.scrollToFirstProduct();
        homePage.clickOnFirstProduct();
        homePage.clickViewCart();
        Assert.assertTrue(cartPage.isCartPageDisplayed());

        cartPage.removeProduct(1);
        Assert.assertEquals(cartPage.getCartEmptyDisplayedText(), "Cart is empty!");
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}