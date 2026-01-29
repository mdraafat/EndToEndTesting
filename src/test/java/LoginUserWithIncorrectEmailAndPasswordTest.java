import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import util.data.User;
import util.framework.Framework;
import util.helper.FileHandler;

@Feature("User Registration")
public class LoginUserWithIncorrectEmailAndPasswordTest {

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
    public void RegisterUser(User user) {
        // Navigate to home
        homePage.navigateToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // Go to login
        homePage.clickSignupLoginLink();
        Assert.assertEquals(loginPage.getLoginIntoAccountDisplayedText(), "Login to your account");

        // Enter login details
        loginPage.enterInCorrectEmailAndPassword(user);
        Assert.assertEquals(loginPage.getWrongCredentialsMessage(), "Your email or password is incorrect!");

    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}