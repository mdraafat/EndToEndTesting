import framework.Framework;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.*;
import page.SignupPage;
import util.FileHelper;
import util.User;

@Epic("User Management")
@Feature("User Registration")
public class TC01RegisterUserTest {

    private Framework framework;
    private SignupPage signupPage;

    @DataProvider(name = "users")
    public User[] userDataProvider() throws Exception {
        FileHelper fileHelper = new FileHelper();
        return fileHelper.getUsers();
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        framework = switch (browser.toLowerCase()) {
            case "firefox" -> Framework.startFirefox();
            case "safari" -> Framework.startSafari();
            case "edge" -> Framework.startEdge();
            default -> Framework.startChrome();
        };

        signupPage = new SignupPage(framework);
    }

    @Test(dataProvider = "users")
    public void RegisterUserTest(User user) {
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

        signupPage.clickDelete();
        Assert.assertEquals(signupPage.getAccountDeletedText(), "ACCOUNT DELETED!");
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }
}