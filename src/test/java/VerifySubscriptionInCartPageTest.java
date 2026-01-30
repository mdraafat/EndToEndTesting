import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CartPage;
import page.HomePage;
import util.framework.Framework;

@Feature("Functionality Tests")
public class VerifySubscriptionInCartPageTest {
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
    public void VerifySubscriptionInHomePage() {
        // Navigate to home
        homePage.navigateToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        cartPage.clickCartLink();
        cartPage.scrollToFooter();
        Assert.assertTrue(cartPage.isSubscriptionDisplayed());

        // enter email and verify subscription
        cartPage.enterEmailForSubscribe("raafat@gmail.com");
        Assert.assertTrue(cartPage.isSubscriptionSuccess());
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}
