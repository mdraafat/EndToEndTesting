import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import util.framework.Framework;

@Feature("Functionality Tests")
public class VerifySubscriptionInHomePageTest {
    private HomePage homePage;

    private Framework framework;

    @BeforeMethod
    public void setup() {
        framework = Framework.start();
        homePage = new HomePage(framework);
    }

    @Test
    public void VerifySubscriptionInHomePage() {
        // Navigate to home
        homePage.navigateToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // verify subscription text is displayed
        homePage.scrollToFooter();
        Assert.assertTrue(homePage.isSubscriptionDisplayed());

        // enter email and verify subscription
        homePage.enterEmailForSubscribe("raafat@gmail.com");
        Assert.assertTrue(homePage.isSubscriptionSuccess());
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}
