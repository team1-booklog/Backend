package goorm.unit.booklog.domain.book.infrastructure;

import goorm.unit.booklog.domain.book.domain.Book;
import org.springframework.stereotype.Repository;

import goorm.unit.booklog.domain.book.domain.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
	private final JpaBookRepository jpaBookRepository;

	@Override
	public Book save(Book book) {
		return jpaBookRepository.save(book);
	}

	@Override
	public Optional<Book> findById(Long id) {
		return jpaBookRepository.findById(id);
	}

	@Override
	public Optional<Book> findByTitleAndAuthor(String title, String author){
		return jpaBookRepository.findByTitleAndAuthor(title,author);
	};
}
