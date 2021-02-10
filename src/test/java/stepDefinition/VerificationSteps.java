package stepDefinition;

import apiEng.IRestResponse;
import apiEngine.model.responses.Book;
import apiEngine.model.responses.userAccount;
import cucumber.testcontext;
import enums.Context;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class VerificationSteps extends BaseStep{

    public VerificationSteps(testcontext tc)
    {
        super(tc);
    }

    @Then("The book is added")
    public void bookIsAdded() {

        Book bok = (Book) getScContext().getContext(Context.BOOK);
        String userID = (String) getScContext().getContext(Context.USER_ID);

        IRestResponse<userAccount> userAccountIRestResponse = (IRestResponse<userAccount>) getScContext().getContext(Context.USER_ACCOUNT_RESPONSE);

        Assert.assertTrue(userAccountIRestResponse.isSuccessful());

        Assert.assertEquals(201,userAccountIRestResponse.getStatusCode());
        Assert.assertEquals(userID,userAccountIRestResponse.getBody().userID);
        Assert.assertEquals(bok.isbn,userAccountIRestResponse.getBody().books.get(0).isbn);
        getScContext().setContext(Context.USER_ACCOUNT_RESPONSE,userAccountIRestResponse);
    }


    @Then("the book is removed")
    public void the_book_is_removed() {

        String userId = (String) getScContext().getContext(Context.USER_ID);
        Response res = (Response) getScContext().getContext(Context.BOOK_REMOVE_RESPONSE);
        Assert.assertEquals(401, res.getStatusCode());



        IRestResponse<userAccount> userAccountIRestResponse = (IRestResponse<userAccount>) getScContext().getContext(Context.BOOK_REMOVE_RESPONSE);
        //userAccount usAc = res.getBody().as(userAccount.class);

        Assert.assertEquals(204,res.getStatusCode());
        Assert.assertEquals(200,userAccountIRestResponse.getStatusCode());
        Assert.assertEquals(0,userAccountIRestResponse.getBody().books.size());
    }


}
