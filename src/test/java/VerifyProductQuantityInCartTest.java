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
public class VerifyProductQuantityInCartTest {

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
    public void VerifyProductQuantityInCart() {
        // Navigate to home
        homePage.goToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // Click on Products Button
        homePage.clickProduct();
        Assert.assertTrue(productPage.isFirstProductDetailsDisplayed());

        // Verify that product is displayed in cart page is 4
        productPage.increaseQuantityTo4();
        productPage.clickAddToCart();
        productPage.clickViewCart();
        Assert.assertEquals(cartPage.qtyofTheItemInCartRow(), "4");
    }

    @AfterMethod
    public void tearDown() {
        framework.closeBrowser();
    }
}