import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CartPage;
import page.HomePage;
import util.framework.Framework;

@Feature("Functionality Tests")
public class AddToCartFromRecommendedItemsTest {
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
    public void AddToCartFromRecommendedItems() {
        // Navigate to home
        homePage.goToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        homePage.ScrollToRecommended();
        Assert.assertTrue(homePage.isRecommendedDisplayed());

        homePage.clickOnRecommendedProduct();
        homePage.clickViewCart();
        Assert.assertEquals(cartPage.noOfItemsInCart(), 1);
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}
