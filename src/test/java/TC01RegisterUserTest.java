import framework.Framework;
import org.testng.Assert;
import org.testng.annotations.*;
import page.SignupPage;
import util.FileHelper;
import util.User;

public class TC01RegisterUserTest {

    private Framework framework;
    private SignupPage signupPage;

    @DataProvider(name = "users")
    public Object[][] getUserData() throws Exception {
        User[] users = new FileHelper().getUsers();
        Object[][] data = new Object[users.length][1];

        int index = 0;
        for (User user : users) {
            data[index][0] = user;
            index++;
        }

        return data;
    }

    @BeforeMethod
    public void setup() {
        framework = Framework.startChrome();
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