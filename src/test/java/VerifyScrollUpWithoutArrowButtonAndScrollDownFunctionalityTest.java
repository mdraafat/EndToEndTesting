import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import util.framework.Framework;

@Feature("Functionality Tests")
public class VerifyScrollUpWithoutArrowButtonAndScrollDownFunctionalityTest {
    private HomePage homePage;

    private Framework framework;

    @BeforeMethod
    public void setup() {
        framework = Framework.start();
        homePage = new HomePage(framework);
    }

    @Test
    public void VerifyScrollUpWithoutArrowButtonAndScrollDown() {
        // Navigate to home
        homePage.goToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // verify subscription text is displayed
        homePage.scrollToFooter();
        Assert.assertTrue(homePage.isSubscriptionDisplayed());

        // scroll to top and verify text
        homePage.scrollToTop();
        Assert.assertTrue(homePage.isMainTextFullyDisplayed());
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}
