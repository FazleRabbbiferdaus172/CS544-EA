package labs.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    private long id;
    private String isbn;
    private String title;
    private String author;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name="book_publisher",
            joinColumns = @JoinColumn(name = "book_isbn_fk"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id_fk")
    )
    private Publisher publisher;

    protected Book(){}

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisherId=" + (publisher != null ? publisher.getId() : "null") +
                '}';
    }
}
