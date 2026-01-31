import page.CartPage;
import page.CheckoutPage;
import util.framework.Framework;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.*;
import page.HomePage;
import page.SignupPage;
import util.helper.FileHandler;
import util.data.User;

@Feature("User Registration")
public class PlaceOrderRegisterBeforeCheckoutTest {

    private HomePage homePage;
    private SignupPage signupPage;

    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    private Framework framework;

    @DataProvider(name = "register_users")
    public User[] userDataProvider() throws Exception {
        return new FileHandler().getRegisterUser();
    }

    @BeforeMethod
    public void setup() {
        framework = Framework.start();
        homePage = new HomePage(framework);
        signupPage = new SignupPage(framework);
        cartPage = new CartPage(framework);
        checkoutPage = new CheckoutPage(framework);
    }

    @Test(dataProvider = "register_users")
    public void PlaceOrderRegisterBeforeCheckout(User user) {
        // Navigate to home
        homePage.goToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // Go to signup
        homePage.clickSignupLoginLink();

        // Enter signup details
        signupPage.enterNameAndEmail(user);

        // Fill form and create account
        signupPage.fillNewAccountAndAddressInformation(user);
        signupPage.createAccount();
        Assert.assertEquals(signupPage.getAccountCreatedText(), "ACCOUNT CREATED!");

        // Continue and getTextOf login
        signupPage.clickContinue();
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