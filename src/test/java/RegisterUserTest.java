import framework.Framework;
import org.testng.Assert;
import org.testng.annotations.*;
import page.SignupPage;
import util.FileHelper;
import util.User;

public class RegisterUserTest {

    private Framework framework;
    private SignupPage signupPage;
    private User user;

    @BeforeClass
    public void setup() throws Exception {
        framework = Framework.startChrome();
        signupPage = new SignupPage(framework);
        user = new FileHelper().getUsers()[0];
    }

    @Test(priority = 1)
    public void verifyHomePageVisible() {
        signupPage.navigateToHome();
        Assert.assertTrue(signupPage.isHomePageDisplayed());
    }

    @Test(priority = 2)
    public void verifyNewUserSignupVisible() {
        signupPage.clickSignupLoginLink();
        Assert.assertEquals(signupPage.getNewUserSignupDisplayedText(), "New User Signup!");
    }

    @Test(priority = 3)
    public void verifyEnterAccountInformationVisible() {
        signupPage.enterEmailAndAddress(user);
        Assert.assertEquals(signupPage.getAccountInformationDisplayedText(), "ENTER ACCOUNT INFORMATION");
    }

    @Test(priority = 4)
    public void verifyAccountCreated() {
        signupPage.fillNewAccountAndAddressInformation(user);
        signupPage.createAccount();
        Assert.assertEquals(signupPage.getAccountCreatedText(), "ACCOUNT CREATED!");
    }

    @Test(priority = 5)
    public void verifyLoggedInAsVisible() {
        signupPage.clickContinue();
        Assert.assertTrue(signupPage.checkLoggedInAsText());
    }

    @Test(priority = 6)
    public void verifyAccountDeleted() {
        signupPage.clickDelete();
        Assert.assertEquals(signupPage.getAccountDeletedText(), "ACCOUNT DELETED!");
    }

    @AfterClass
    public void teardown() {
        framework.closeBrowser();
    }
}