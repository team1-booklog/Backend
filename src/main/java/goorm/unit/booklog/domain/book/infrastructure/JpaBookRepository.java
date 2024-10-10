package goorm.unit.booklog.domain.book.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import goorm.unit.booklog.domain.book.domain.Book;
import java.util.Optional;


public interface JpaBookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthor(String title, String author);
}
