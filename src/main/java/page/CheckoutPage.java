package page;

import util.data.User;
import util.framework.Framework;

public class CheckoutPage {

    private final Framework framework;

    private static final String DELIVERY_ADDR_NAME = "#address_delivery .address_city.address_state_name.address_postcode";
    private static final String BILLING_ADDR_NAME = "#address_invoice .address_city.address_state_name.address_postcode";


    public CheckoutPage(Framework framework) {
        this.framework = framework;
    }


    public boolean sameDeliveryAddressInfo(User user) {
        return framework.getTextOf(DELIVERY_ADDR_NAME).equals(user.getCity() + " " + user.getState() + " " + user.getZipcode());
    }

    public boolean sameBillingAddressInfo(User user) {
        return framework.getTextOf(BILLING_ADDR_NAME).equals(user.getCity() + " " + user.getState() + " " + user.getZipcode());
    }
}
