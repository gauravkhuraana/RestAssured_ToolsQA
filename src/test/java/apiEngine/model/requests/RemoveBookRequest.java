package apiEngine.model.requests;

public class RemoveBookRequest {

    public String isbn;
    public String userId;

    /**
     * No args constructor for use in serialization
     *
     */
    public RemoveBookRequest() {
    }

    /**
     *
     * @param isbn
     * @param userId
     */
    public RemoveBookRequest(String isbn, String userId) {
        super();
        this.isbn = isbn;
        this.userId = userId;
    }

}