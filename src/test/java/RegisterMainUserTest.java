import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.*;
import page.SignupPage;
import util.helper.FileHandler;
import util.data.User;
import util.framework.Framework;

@Feature("User Registration")
public class RegisterMainUserTest {

    private Framework framework;
    private SignupPage signupPage;

    @DataProvider(name = "mainUser")
    public User[] userDataProvider() throws Exception {
        FileHandler fileHandler = new FileHandler();
        return fileHandler.getMainUser();
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        framework = Framework.start(browser);
        signupPage = new SignupPage(framework);
    }

    @Test(dataProvider = "mainUser")
    public void RegisterMainUser(User user) {
        signupPage.navigateToHome();
        Assert.assertTrue(signupPage.isHomePageDisplayed());

        signupPage.clickSignupLoginLink();
        Assert.assertEquals(signupPage.getNewUserSignupDisplayedText(), "New User Signup!");

        signupPage.enterEmailAndAddress(user);
        Assert.assertEquals(signupPage.getAccountInformationDisplayedText(), "ENTER ACCOUNT INFORMATION");

        signupPage.fillNewAccountAndAddressInformation(user);
        signupPage.createAccount();
        Assert.assertEquals(signupPage.getAccountCreatedText(), "ACCOUNT CREATED!");

        signupPage.clickContinue();
        Assert.assertTrue(signupPage.checkLoggedInAsText());
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}