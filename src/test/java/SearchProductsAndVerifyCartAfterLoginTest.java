import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.CartPage;
import page.HomePage;
import page.LoginPage;
import page.ProductPage;
import util.data.User;
import util.framework.Framework;
import util.helper.FileHandler;

@Feature("Functionality Tests")
public class SearchProductsAndVerifyCartAfterLoginTest {

    private HomePage homePage;

    private ProductPage productPage;

    private CartPage cartPage;

    private LoginPage loginPage;
    private Framework framework;

    @DataProvider(name = "main_users")
    public User[] userDataProvider() throws Exception {
        FileHandler fileHandler = new FileHandler();
        return fileHandler.getMainUser();
    }

    @BeforeMethod
    public void setup() {
        framework = Framework.start();
        homePage = new HomePage(framework);
        productPage = new ProductPage(framework);
        cartPage = new CartPage(framework);
        loginPage = new LoginPage(framework);
    }

    @Test(dataProvider = "main_users")
    public void SearchProductsAndVerifyCartAfterLogin(User user) {
        // Navigate to home
        homePage.goToHome();
        // Click on Products Button
        productPage.clickProductsLink();
        Assert.assertTrue(productPage.isProductsPageDisplayed());

        // search for shirt products
        String searchTerm = "shirt";
        productPage.searchForProductName(searchTerm);
        productPage.clickSearchButton();
        Assert.assertTrue(productPage.isSearchedProductsDisplayed());

        productPage.clickAddToCartForAllResults();

        productPage.scrollToHeader();

        int numberOfResults = productPage.numberOfProductsInSearchResult();

        cartPage.clickCartLink();

        Assert.assertEquals(cartPage.noOfItemsInCart(), numberOfResults);

        homePage.clickSignupLoginLink();
        loginPage.enterEmailAndPassword(user);

        cartPage.clickCartLink();
        Assert.assertEquals(cartPage.noOfItemsInCart(), numberOfResults);


    }

    @AfterMethod
    public void tearDown() {
        framework.closeBrowser();
    }
}