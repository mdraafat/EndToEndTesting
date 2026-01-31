import page.*;
import util.framework.Framework;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.*;
import util.helper.FileHandler;
import util.data.User;

@Feature("User Registration")
public class PlaceOrderLoginBeforeCheckoutTest {

    private HomePage homePage;
    private LoginPage loginPage;

    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    private Framework framework;

    @DataProvider(name = "login_users")
    public User[] userDataProvider() throws Exception {
        return new FileHandler().getLoginUser();
    }

    @BeforeMethod
    public void setup() {
        framework = Framework.start();
        homePage = new HomePage(framework);
        loginPage = new LoginPage(framework);
        cartPage = new CartPage(framework);
        checkoutPage = new CheckoutPage(framework);
    }

    @Test(dataProvider = "login_users")
    public void PlaceOrderLoginBeforeCheckout(User user) {
        // Navigate to home
        homePage.goToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // Go to login
        homePage.clickSignupLoginLink();

        // Enter login details
        loginPage.enterEmailAndPassword(user);
        Assert.assertTrue(homePage.checkLoggedInAsText());


        homePage.scrollToFirstProduct();
        homePage.clickOnFirstProduct();
        homePage.clickViewCart();
        Assert.assertTrue(cartPage.isCartPageDisplayed());

        cartPage.clickProceedToCheckout();

        cartPage.clickCartLink();
        cartPage.clickProceedToCheckout();
        Assert.assertTrue(checkoutPage.VerifyAddressDetailsAndReviewOrder());

        checkoutPage.addCommentAboutOrder();
        checkoutPage.clickPlaceOrder();

        checkoutPage.enterPaymentDetails();
        checkoutPage.clickOnPayButton();
        Assert.assertEquals(checkoutPage.getSuccessMessage(), "Your order has been placed successfully!");


        // Delete account
        homePage.clickDelete();
        Assert.assertEquals(homePage.getAccountDeletedText(), "ACCOUNT DELETED!");
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}