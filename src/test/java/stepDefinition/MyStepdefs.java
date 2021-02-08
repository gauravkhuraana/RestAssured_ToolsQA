package stepDefinition;


import apiEngine.model.requests.AuthorizationRequest;
import apiEngine.model.responses.Book;
import apiEngine.model.responses.Books;
import apiEngine.model.responses.token;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.junit.Assert;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class MyStepdefs {

    private static final String USER_ID="c88239f0-d784-4c13-beec-58ebb2aa2a23";
    private static final String USERNAME="HareKrishna";
    private static final String PASSWORD="HariBol@123";
    private static final String BASE_URL="https://bookstore.toolsqa.com";

        private static String token;
        private static Response response;
        private static String jsonString;
        private static String bookId;
       private static Book book;


    @Given("I am an authorised user")
    public void i_am_an_authorised_user() {
            System.out.println("hello");

        AuthorizationRequest authReq = new AuthorizationRequest("HareKrishna","HariBol@123");

            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();

            request.header("Content-Type", "application/json");
          //  response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
           //         .post("/Account/v1/GenerateToken");

        response = request.body(authReq)
                .post("/Account/v1/GenerateToken");


            //String jsonString = response.asString();
            //token = JsonPath.from(jsonString).get("token");


          //  token=response.getBody().as((Type) token.class);


           // System.out.println("token is "+ token);
        }

        @Given("A list of books are available")
        public void listOfBooksAreAvailable() {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();
            response = request.get("/BookStore/v1/Books");

            jsonString = response.asString();
            List<Map<String, String>> books = JsonPath.from(jsonString).get("books");
           // Assert.assertTrue(books.size() > 0);

            bookId = books.get(0).get("isbn");

          //  Books boo = response.getBody().as(Books.class);
          //  book = boo.books.get(0);
            System.out.println("Book "+ book);

        }

        @When("I add a book to my reading list")
        public void addBookInList() {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();
            request.header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json");

            response = request.body("{ \"userId\": \"" + USER_ID + "\", " +
                    "\"collectionOfIsbns\": [ { \"isbn\": \"" + bookId + "\" } ]}")
                    .post("/BookStore/v1/Books");
        }

        @Then("The book is added")
        public void bookIsAdded() {
           // Assert.assertEquals(201, response.getStatusCode());
        }

        @When("I remove a book from my reading list")
        public void removeBookFromList() {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();

            request.header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json");

            response = request.body("{ \"isbn\": \"" + bookId + "\", \"userId\": \"" + USER_ID + "\"}")
                    .delete("/BookStore/v1/Book");


        }

    @Then("the book is removed")
    public void the_book_is_removed() {
            Assert.assertEquals(401, response.getStatusCode());

            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();

            request.header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json");

            response = request.get("/Account/v1/User/" + USER_ID);
         //   Assert.assertEquals(200, response.getStatusCode());

            jsonString = response.asString();
         //   List<Map<String, String>> booksOfUser = JsonPath.from(jsonString).get("books");
           // Assert.assertEquals(0, booksOfUser.size());
        }






}
