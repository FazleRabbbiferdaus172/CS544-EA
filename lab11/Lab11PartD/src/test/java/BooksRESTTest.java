import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import books.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class BooksRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testAddBook() {
        Book book = new Book("999", "A Brand New Book", 34.95, "New Author");

        given()
                .contentType(ContentType.JSON).body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(200)
                .body("isbn", equalTo("999"));
    }

    @Test
    public void testGetOneBook() {
        // add the book to be fetched
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/878")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 123"))
                .body("price",equalTo(18.95f))
                .body("author",equalTo("Joe Smith"));
        //cleanup
        given()
                .when()
                .delete("books/878");
    }

    @Test
    public void testUpdateBook() {
        Book originalBook = new Book("767", "Original Title", 50.00, "Original Author");
        given()
                .contentType(ContentType.JSON)
                .body(originalBook)
                .when()
                .post("/books")
                .then()
                .statusCode(200);
        Book updatedBook = new Book("767", "Updated Title", 55.50, "Updated Author");
        given()
                .contentType(ContentType.JSON)
                .body(updatedBook)
                .when()
                .put("books/767")
                .then()
                .statusCode(200)
                .body("title", equalTo(updatedBook.getTitle()))
                .body("price", equalTo(55.50f));
        given()
                .when()
                .delete("/books/767");
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("454", "Another Book", 20.00, "Some Author");
        given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(200);
        given()
                .when()
                .delete("/books/454")
                .then()
                .statusCode(204);
        given()
                .when()
                .get("/books/454")
                .then()
                .statusCode(404);
    }

    @Test
    public void testSearchBooksByAuthor() {
        Book book = new Book("323", "A Searchable Book", 15.00, "Searchable Author");
        given()
                .contentType(ContentType.JSON).body(book)
                .when().post("/books")
                .then().statusCode(200);

        given()
                .when()
                .get("/books?author=\"Searchable Author\"")
                .then()
                .statusCode(200)
                .body("books[0].isbn", equalTo(book.getIsbn()));
        given()
                .when().delete();
    }

    @Test
    public void testGetAllBooks() {
        given()
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("books", instanceOf(java.util.List.class));
    }


}
