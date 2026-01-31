import page.LoginPage;
import util.framework.Framework;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.*;
import page.HomePage;
import util.helper.FileHandler;
import util.data.User;

@Feature("User Registration")
public class LoginUserWithCorrectEmailAndPasswordTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private Framework framework;

    @DataProvider(name = "login_users")
    public User[] userDataProvider() throws Exception {
        FileHandler fileHandler = new FileHandler();
        return fileHandler.getLoginUser();
    }

    @BeforeMethod
    public void setup() {
        framework = Framework.start();
        homePage = new HomePage(framework);
        loginPage = new LoginPage(framework);
    }

    @Test(dataProvider = "login_users")
    public void LoginWithValidCredentialsThenDelete(User user) {
        // Navigate to home
        homePage.goToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // Go to login
        homePage.clickSignupLoginLink();
        Assert.assertEquals(loginPage.getLoginIntoAccountDisplayedText(), "Login to your account");

        // Enter login details
        loginPage.enterEmailAndPassword(user);
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