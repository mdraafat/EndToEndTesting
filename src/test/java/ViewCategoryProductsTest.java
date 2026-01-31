import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import util.framework.Framework;

@Feature("Functionality Tests")
public class ViewCategoryProductsTest {
    private HomePage homePage;

    private Framework framework;

    @BeforeMethod
    public void setup() {
        framework = Framework.start();
        homePage = new HomePage(framework);
    }

    @Test
    public void ViewCategoryProducts() {
        // Navigate to home
        homePage.goToHome();
        homePage.scrollToCategories();
        Assert.assertTrue(homePage.isCategoriesSectionVisible());

        homePage.clickOnWomenCategory();
        homePage.clickOnDressCategory();
        Assert.assertEquals(homePage.getWomenDressCategoryText(), "WOMEN - DRESS PRODUCTS");

        homePage.clickOnMenCategory();
        homePage.clickOnTshirtCategory();
        Assert.assertEquals(homePage.getMenTshirtCategoryText(), "MEN - TSHIRTS PRODUCTS");

    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}
