import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import util.framework.Framework;

public class TestCasesPageTest {
    private HomePage homePage;

    private Framework framework;

    @BeforeMethod
    public void setup() {
        framework = Framework.start();
        homePage = new HomePage(framework);
    }

    @Test
    public void TestCasesPage() {
        // Navigate to home
        homePage.navigateToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // Click Test Cases link
        homePage.clickTestCasesLink();
        Assert.assertTrue(homePage.isTestCasesDisplayed());
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}
