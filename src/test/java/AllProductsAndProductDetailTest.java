import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.ProductPage;
import util.framework.Framework;

@Feature("Functionality Tests")
public class AllProductsAndProductDetailTest {

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
    public void AllProductsAndProductDetailPage() {
        // Navigate to home
        homePage.navigateToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // Click on Products Button
        productPage.clickProductsLink();
        Assert.assertTrue(productPage.isProductsPageDisplayed());

        // Product List Displayed
        Assert.assertTrue(productPage.isProductListDisplayed());

        // Check details of first product
        productPage.clickProduct();
        Assert.assertTrue(productPage.isFirstProductDetailsDisplayed());
        Assert.assertTrue(productPage.areFirstProductDetailsInformationDisplayed());
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }

}
