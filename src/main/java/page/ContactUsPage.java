package page;

import util.framework.Framework;

public class ContactUsPage {

    private final Framework framework;

    private static final String GET_IN_TOUCH = "div.contact-form > h2.title.text-center";
    private static final String NAME_FIELD = "[data-qa='name']";
    private static final String EMAIL_FIELD = "[data-qa='email']";
    private static final String SUBJECT_FIELD = "[data-qa='subject']";
    private static final String MESSAGE_FIELD = "[data-qa='message']";

    private static final String UPLOAD_FILE = "[name='upload_file']";

    private static final String SUBMIT_BUTTON = "[data-qa='submit-button']";

    private static final String SUCCESS_MESSAGE = "div.status.alert.alert-success";

    private static final String SUCCESS_BUTTON = "#form-section > a.btn.btn-success";

    public ContactUsPage(Framework framework) {
        this.framework = framework;
    }

    public String getGetInTouchMessage() {
        return framework.getTextOf(GET_IN_TOUCH);
    }

    public void fillInFields() {
        framework.sendTo(NAME_FIELD, "Raafat");
        framework.sendTo(EMAIL_FIELD, "raafat@gmail.co.uk");
        framework.sendTo(SUBJECT_FIELD, "Hello World");
        framework.sendTo(MESSAGE_FIELD, "Thank you Edges Academy");
    }

    public void uploadFile() {
        framework.sendTo(UPLOAD_FILE, "/home/raafat/Pictures/Screenshots/Screenshot From 2026-01-23 14-52-11.png");
    }

    public void clickSubmitButton() {
        framework.clickOn(SUBMIT_BUTTON);
        framework.acceptPrompt();
        framework.removeAds();
    }

    public boolean isSuccessMessageDisplayed() {
        return framework.isPresent(SUCCESS_MESSAGE);
    }

    public void clickSuccessButton() {
        framework.clickOn(SUCCESS_BUTTON);
    }
}
