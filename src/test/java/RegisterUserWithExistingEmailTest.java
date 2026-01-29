import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.SignupPage;
import util.data.User;
import util.framework.Framework;
import util.helper.FileHandler;

@Feature("User Registration")
public class RegisterUserWithExistingEmailTest {

    private HomePage homePage;
    private SignupPage signupPage;
    private Framework framework;

    @DataProvider(name = "register_users")
    public User[] userDataProvider() throws Exception {
        FileHandler fileHandler = new FileHandler();
        return fileHandler.getMainUser();
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
        Assert.assertEquals(signupPage.getAlreadyExistMessage(), "Email Address already exist!");


    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}