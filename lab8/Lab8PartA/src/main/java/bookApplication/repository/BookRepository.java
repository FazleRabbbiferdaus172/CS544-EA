package bookApplication.repository;

import bookApplication.domain.Book;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepositoryImplementation<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

    void deleteByIsbn(String isbn);

    List<Book> findByAuthor(String author);
}
