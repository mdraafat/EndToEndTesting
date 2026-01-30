import util.framework.Framework;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.*;
import page.HomePage;
import page.SignupPage;
import util.helper.FileHandler;
import util.data.User;

@Feature("User Registration")
public class RegisterUserTest {

    private HomePage homePage;
    private SignupPage signupPage;
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
    }

    @Test(dataProvider = "register_users")
    public void RegisterUser(User user) {
        // Navigate to home
        homePage.navigateToHome();
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

        // Delete account
        homePage.clickDelete();
        Assert.assertEquals(homePage.getAccountDeletedText(), "ACCOUNT DELETED!");
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}