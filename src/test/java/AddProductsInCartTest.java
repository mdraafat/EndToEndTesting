import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CartPage;
import page.HomePage;
import page.ProductPage;
import util.framework.Framework;


@Feature("Functionality Tests")
public class AddProductsInCartTest {

    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private Framework framework;

    @BeforeMethod
    public void setup() {
        framework = Framework.start();
        homePage = new HomePage(framework);
        productPage = new ProductPage(framework);
        cartPage = new CartPage(framework);
    }

    @Test
    public void addProductsInCart() {
        // Navigate to home
        homePage.goToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // Click on Products Cart Buttons and verify items in cart
        productPage.clickProductsLink();
        productPage.scrollToFirstProduct();
        productPage.hoverAndClickOnFirstProduct();
        productPage.clickContinueShopping();
        productPage.hoverAndClickOnSecondProduct();
        productPage.clickViewCart();
        Assert.assertEquals(cartPage.noOfItemsInCart(), 2);

        // verify the price * quantity = total
        Assert.assertTrue(cartPage.verifyFirstItemTotal());
        Assert.assertTrue(cartPage.verifySecondItemTotal());
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}
