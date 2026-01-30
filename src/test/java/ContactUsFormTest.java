import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.ContactUsPage;
import page.HomePage;
import util.framework.Framework;

@Feature("Functionality Tests")
public class ContactUsFormTest {

    private HomePage homePage;

    private ContactUsPage contactUsPage;

    private Framework framework;


    @BeforeMethod
    public void setup() {
        framework = Framework.start();
        homePage = new HomePage(framework);
        contactUsPage = new ContactUsPage(framework);
    }

    @Test
    public void ContactUsForm() {
        // Navigate to home
        homePage.navigateToHome();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        // Verify Get in Touch message
        homePage.clickContactUsLink();
        Assert.assertEquals(contactUsPage.getGetInTouchMessage().toUpperCase(), "GET IN TOUCH");

        // fill in fields and accept prompt
        contactUsPage.fillInFields();
        contactUsPage.uploadFile();
        contactUsPage.clickSubmitButton();
        Assert.assertTrue(contactUsPage.isSuccessMessageDisplayed());


        // click on success button
        contactUsPage.clickSuccessButton();
        Assert.assertTrue(homePage.isHomePageDisplayed());

    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }

}
