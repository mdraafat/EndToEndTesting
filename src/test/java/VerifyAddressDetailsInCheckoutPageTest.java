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
public class VerifyAddressDetailsInCheckoutPageTest {

    private HomePage homePage;
    private SignupPage signupPage;

    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    private Framework framework;

    @DataProvider(name = "register_users")
    public User[] userDataProvider() throws Exception {
        FileHandler fileHandler = new FileHandler();
        return fileHandler.getRegisterUser();
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
    public void RegisterUser(User user) {
        // Navigate to home
        homePage.goToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // Go to signup
        homePage.clickSignupLoginLink();
        Assert.assertEquals(signupPage.getNewUserSignupDisplayedText(), "New User Signup!");

        // Enter signup details
        signupPage.enterNameAndEmail(user);
        Assert.assertEquals(signupPage.getAccountInformationDisplayedText(), "ENTER ACCOUNT INFORMATION");

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

        Assert.assertTrue(checkoutPage.sameDeliveryAddressInfo(user));
        Assert.assertTrue(checkoutPage.sameBillingAddressInfo(user));

        // Delete account
        homePage.clickDelete();
        Assert.assertEquals(homePage.getAccountDeletedText(), "ACCOUNT DELETED!");
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}