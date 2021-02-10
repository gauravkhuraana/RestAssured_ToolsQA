/*
package stepDefinition;


import apiEng.Endpoints;
import apiEng.IRestResponse;
import apiEng.Route;
import apiEngine.model.requests.AddBooksRequest;
import apiEngine.model.requests.AuthorizationRequest;
import apiEngine.model.requests.ISBN;
import apiEngine.model.requests.RemoveBookRequest;
import apiEngine.model.responses.Book;
import apiEngine.model.responses.Books;
import apiEngine.model.responses.token;
import apiEngine.model.responses.userAccount;
import com.sun.org.apache.regexp.internal.RE;
import io.cucumber.java.af.En;
import io.cucumber.java.an.E;
import io.cucumber.java.bs.A;
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

//    private static final String USER_ID="c88239f0-d784-4c13-beec-58ebb2aa2a23";
    private static final String USERNAME="HareKrishna";
    private static final String PASSWORD="HariBol@1234";
    private static final String BASE_URL="https://bookstore.toolsqa.com";

        private static String token;
        private static Response response;
        private static String jsonString;
        private static String bookId;
       private static Book book;

    private static final String USER_ID="effb7fbb-c4e4-4e4d-9f24-53ff5a0215f7";
    private static Response res;
    private static token tokRes;
    private static Book bok;

    private static IRestResponse<userAccount> userAccountIRestResponse;

    private Endpoints endpoint;

    @Given("I am an authorised user")
    public void i_am_an_authorised_user() {
            System.out.println("hello");


   RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();

            request.header("Content-Type", "application/json");

   //  response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
           //         .post("/Account/v1/GenerateToken");

        //AuthorizationRequest authReq = new AuthorizationRequest("HareKrishna","HariBol@1234");
       // response = request.body(authReq)
        //        .post(Route.generateToken());
//


        // #3rd Optimisation
        //AuthorizationRequest auth=new AuthorizationRequest("HareKrishna","HariBol@1234");
      //  res= Endpoints.authenticateUser(auth).getBody();
//        tokRes=res.getBody().as(token.class);
  //      System.out.println("token is "+ tokRes.token);


       // 4th optimisation
        endpoint= new Endpoints(BASE_URL);
        AuthorizationRequest auth=new AuthorizationRequest("HareKrishna","HariBol@1234");
        //tokRes= Endpoints.authenticateUser(auth).getBody();
        endpoint.authenticateUser(auth);
       //  System.out.println("token is "+ tokRes.token);


        // Old Method
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


        //    jsonString = response.asString();
          //  List<Map<String, String>> books = JsonPath.from(jsonString).get("books");
           // Assert.assertTrue(books.size() > 0);

           // bookId = books.get(0).get("isbn");

          //  Books boo = response.getBody().as(Books.class);
          //  book = boo.books.get(0);
            System.out.println("Book "+ bookId);

            IRestResponse<Books> bookres= endpoint.getBooks();
//            bok=bookres.getBody().books.get(0);

            //Books bookies = res.getBody().as(Books.class);
            //bok=bookies.books.get(0);
            //System.out.println("Bok id isbn is "+ bok.isbn.toString());

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


            ISBN isbn=new ISBN(bok.isbn);
            AddBooksRequest adbkreq =  new AddBooksRequest(USER_ID,isbn);
          //  res=Endpoints.addBook(adbkreq,tokRes.token);
            userAccountIRestResponse = endpoint.addBook(adbkreq,tokRes.token);

        }

        @Then("The book is added")
        public void bookIsAdded() {
           // Assert.assertEquals(201, response.getStatusCode());
            userAccount uAcc = res.getBody().as(userAccount.class);

            Assert.assertEquals(201,userAccountIRestResponse.getStatusCode());
            Assert.assertEquals(USER_ID,userAccountIRestResponse.getBody().userID);
            Assert.assertEquals(bok.isbn,userAccountIRestResponse.getBody().books.get(0).isbn);

        }

        @When("I remove a book from my reading list")
        public void removeBookFromList() {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();

            request.header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json");

            response = request.body("{ \"isbn\": \"" + bookId + "\", \"userId\": \"" + USER_ID + "\"}")
                    .delete("/BookStore/v1/Book");

            RemoveBookRequest removeBook = new RemoveBookRequest(USER_ID,book.isbn);
            res=endpoint.removeBook(removeBook,tokRes.token);


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


        userAccountIRestResponse =endpoint.getUserAccount(USER_ID,tokRes.token);
        //userAccount usAc = res.getBody().as(userAccount.class);

        Assert.assertEquals(204,res.getStatusCode());
        Assert.assertEquals(200,userAccountIRestResponse.getStatusCode());
        Assert.assertEquals(0,userAccountIRestResponse.getBody().books.size());
        }






}
*/
