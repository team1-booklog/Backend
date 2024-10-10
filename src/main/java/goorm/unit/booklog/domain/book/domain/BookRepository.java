package goorm.unit.booklog.domain.book.domain;


import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(Long id);

    Optional<Book> findByTitleAndAuthor(String title, String author);

}
