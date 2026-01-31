import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.ProductPage;
import util.framework.Framework;

@Feature("Functionality Tests")
public class SearchProductTest {
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
    public void SearchProduct() {
        // Navigate to home
        homePage.goToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // Click on Products Button
        productPage.clickProductsLink();
        Assert.assertTrue(productPage.isProductsPageDisplayed());

        // search for shirt products and verify results related
        String searchTerm = "shirt";
        productPage.searchForProductName(searchTerm);
        productPage.clickSearchButton();
        Assert.assertTrue(productPage.isSearchedProductsDisplayed());

        Assert.assertTrue(productPage.checkSearchedProducts(searchTerm));

    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}
