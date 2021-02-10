package stepDefinition;

import apiEngine.model.requests.AuthorizationRequest;
import cucumber.testcontext;
import io.cucumber.java.en.Given;

public class AccountSteps extends BaseStep {

    public AccountSteps(testcontext tc) {
        super(tc);
    }

    @Given("I am an authorised user")
    public void i_am_an_authorised_user() {
        System.out.println("hello");
        AuthorizationRequest auth = new AuthorizationRequest("HareKrishna", "HariBol@1234");
        getEndPoints().authenticateUser(auth);

    }
}
