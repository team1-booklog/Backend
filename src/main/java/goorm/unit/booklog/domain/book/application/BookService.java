package goorm.unit.booklog.domain.book.application;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import goorm.unit.booklog.domain.book.domain.Book;
import goorm.unit.booklog.domain.file.domain.File;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import goorm.unit.booklog.common.response.PageableResponse;
import goorm.unit.booklog.domain.book.domain.BookRepository;
import goorm.unit.booklog.domain.book.presentation.response.BookPageResponse;
import goorm.unit.booklog.domain.book.presentation.response.BookResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;

	@Value("${naver.api.clientId}")
	private String clientId;

	@Value("${naver.api.clientSecret}")
	private String clientSecret;

	public BookPageResponse searchBooks(int page, int size, String keyword) {
		URI uri = UriComponentsBuilder
			.fromUriString("https://openapi.naver.com")
			.path("/v1/search/book.json")
			.queryParam("query", keyword)
			.queryParam("display", size)
			.queryParam("start", page)
			.queryParam("sort", "sim")
			.encode()
			.build()
			.toUri();

		RequestEntity<Void> req = RequestEntity
			.get(uri)
			.header("X-Naver-Client-Id", clientId)
			.header("X-Naver-Client-Secret", clientSecret)
			.build();

		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.exchange(req, String.class).getBody();

		JSONObject jsonResponse = new JSONObject(response);
		JSONArray items = jsonResponse.getJSONArray("items");

		List<BookResponse> bookResponses = new ArrayList<>();
		for (int i = 0; i < items.length(); i++) {
			JSONObject item = items.getJSONObject(i);

			Long id;
			String title = item.getString("title");
			String author = item.getString("author");
			String description=item.getString("description");
			String link=item.getString("link");

			Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(title, author);

			if (!existingBook.isPresent()) {
				File file = File.of(
						title, // logical name을 title로 지정하였음
						link
				);

				Book book = Book.create(
						title,
						author,
						description,
						file
				);

				bookRepository.save(book);
				id = book.getId();
			}
			else{
				id = existingBook.get().getId();
			}



			BookResponse bookResponse = BookResponse.of(
					id,
					title,
					author,
					description,
					link
			);
			bookResponses.add(bookResponse);
		}

		int total = jsonResponse.getInt("total");
		return BookPageResponse.of(bookResponses, PageableResponse.of(PageRequest.of(page, size), (long)total));

	}

	public Book getBook(long id) {
		return bookRepository.findById(id).orElse(null);
	}

}
