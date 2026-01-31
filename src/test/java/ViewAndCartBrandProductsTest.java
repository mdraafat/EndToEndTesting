import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.ProductPage;
import util.framework.Framework;

@Feature("Functionality Tests")
public class ViewAndCartBrandProductsTest {
    private HomePage homePage;
    private ProductPage productPage;

    private Framework framework;

    @BeforeMethod
    public void setup() {
        framework = Framework.start();
        homePage = new HomePage(framework);
        productPage = new ProductPage(framework);
    }

    @Test
    public void ViewAndCartBrandProducts() {
        // Navigate to home
        homePage.goToHome();
        productPage.clickProductsLink();
        productPage.scrollToBrands();
        Assert.assertTrue(productPage.isBrandsSectionVisible());

        productPage.clickOnFirstBrandName();
        Assert.assertEquals(productPage.getFirstBrandNameText(), "BRAND - POLO PRODUCTS");

        productPage.clickOnSecondBrandName();
        Assert.assertEquals(productPage.getSecondBrandNameText(), "BRAND - H&M PRODUCTS");

    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}
