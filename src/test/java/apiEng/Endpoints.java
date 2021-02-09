package apiEng;

import apiEngine.model.requests.AddBooksRequest;
import apiEngine.model.requests.AuthorizationRequest;
import apiEngine.model.requests.RemoveBookRequest;
import apiEngine.model.responses.Books;
import apiEngine.model.responses.token;
import apiEngine.model.responses.userAccount;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class Endpoints {

    private static final String BASE_URL="https://bookstore.toolsqa.com";

    private final RequestSpecification req;

    public Endpoints(String baseUrl)
    {
        RestAssured.baseURI=baseUrl;
        req=RestAssured.given();
        req.header("Content-Type","application/json");

    }

    public  IRestResponse<token> authenticateUser(AuthorizationRequest auth)
    {
        RestAssured.baseURI=BASE_URL;
        RequestSpecification req = RestAssured.given();
        req.header("Content-type","application/json");

        //Response response = req.body(auth).log().all().post("/Account/v1/GenerateToken");

        Response response = req.body(auth).log().all().post(Route.generateToken());
        //return response;
        return new RestResponse(token.class,response);
    }
    public  IRestResponse<Books> getBooks()
    {
      /*  RestAssured.baseURI=BASE_URL;
        RequestSpecification req = RestAssured.given();
        req.header("Content-Type","application/json");*/
//        Response response = req.log().all().get("/BookStore/v1/Books");
        Response response = req.log().all().get(Route.generateToken());

        return  new RestResponse(Books.class,response);
    }
    public  IRestResponse<userAccount> addBook(AddBooksRequest addBooks,String token)
    {       /*RestAssured.baseURI=BASE_URL;
        RequestSpecification req = RestAssured.given();
        req.header("Content-Type","application/json")
                .header("Authorization","Bearer "+token);*/
        System.out.println("****  Adding Books Request is  **** ");
        //Response response = req.log().all().body(addBooks)
             //   .post("/BookStore/v1/Books");

        Response response = req.log().all().body(addBooks)
                .post(Route.books());
        return  new RestResponse(userAccount.class,response);
    }

    public  Response removeBook(RemoveBookRequest removeBook , String token)
    {
      /*  RestAssured.baseURI=BASE_URL;
        RequestSpecification req = RestAssured.given();
        req.header("Content-Type","application/json")
                .header("Authorization","Bearer "+token);*/
        Response response = req.log().all().body(removeBook).delete(Route.books());
        return  response;
    }

    public  IRestResponse<userAccount> getUserAccount(String userId,String token)
    {
        RestAssured.baseURI=BASE_URL;
      /*  RequestSpecification req = RestAssured.given();
        req.header("Content-Type","application/json")
                .header("Authorization","Bearer "+token);*/
        Response response = req.log().all().get(Route.userAccount(userId));
        return  new RestResponse<>(userAccount.class, response);
    }
}
