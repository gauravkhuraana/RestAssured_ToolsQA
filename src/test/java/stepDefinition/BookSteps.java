package stepDefinition;

import apiEng.IRestResponse;
import apiEngine.model.requests.AddBooksRequest;
import apiEngine.model.requests.ISBN;
import apiEngine.model.requests.RemoveBookRequest;
import apiEngine.model.responses.Book;
import apiEngine.model.responses.Books;
import apiEngine.model.responses.token;
import apiEngine.model.responses.userAccount;
import cucumber.testcontext;
import enums.Context;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class BookSteps extends BaseStep{

    public BookSteps(testcontext tc)
    {
        super(tc);
    }

    private static final String USER_ID="effb7fbb-c4e4-4e4d-9f24-53ff5a0215f7";
    private static Response res;
    private static token tokRes;
    private static Book bok;
    private static IRestResponse<userAccount> userAccountIRestResponse;


    @Given("A list of books are available")
    public void listOfBooksAreAvailable() {
        System.out.println("Book "  );

        IRestResponse<Books> bookres = getEndPoints().getBooks();
        bok=bookres.getBody().books.get(0);
        getScContext().setContext(Context.BOOK,bok);

    }

    @When("I add a book to my reading list")
    public void addBookInList() {
        ISBN isbn=new ISBN(bok.isbn);
        AddBooksRequest adbkreq =  new AddBooksRequest(USER_ID,isbn);
        userAccountIRestResponse = getEndPoints().addBook(adbkreq,tokRes.token);
        getScContext().setContext(Context.USER_ACCOUNT_RESPONSE,userAccountIRestResponse);
    }



    @When("I remove a book from my reading list")
    public void removeBookFromList() {

        RemoveBookRequest removeBook = new RemoveBookRequest(USER_ID,bok.isbn);
        res=getEndPoints().removeBook(removeBook,tokRes.token);
        getScContext().setContext(Context.BOOK_REMOVE_RESPONSE,res);

        Assert.assertEquals(04,res.getStatusCode());


    }


}

