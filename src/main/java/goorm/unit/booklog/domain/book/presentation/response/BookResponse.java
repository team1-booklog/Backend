package goorm.unit.booklog.domain.book.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import goorm.unit.booklog.domain.book.domain.Book;
import goorm.unit.booklog.domain.file.presentation.response.FileResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record BookResponse(
	@Schema(description = "도서 ID", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "도서 제목", example = "스프링 부트와 AWS로 혼자 구현하는 웹 서비스", requiredMode = REQUIRED)
	String title,

	@Schema(description = "도서 저자", example = "이한음", requiredMode = NOT_REQUIRED)
	String author,

	@Schema(description = "도서 설명", example = "스프링 부트와 AWS로 혼자 구현하는 웹 서비스", requiredMode = NOT_REQUIRED)
	String description,

	@Schema(
		description = "파일 정보",
		example = "{\"id\": 1, \"logicalName\": \"example.jpg\", \"physicalPath\": \"https://example-bucket.ncp.com/files/example.jpg\"}",
		requiredMode = NOT_REQUIRED)
	FileResponse file
) {

	public static BookResponse from(Book book) {
		return BookResponse.builder()
			.id(book.getId())
			.title(book.getTitle())
			.author(book.getAuthor())
			.description(book.getDescription())
			.file(FileResponse.from(book.getFile()))
			.build();
	}

	public static BookResponse of (Long id, String title, String author, String description, String physicalPath) {
		return BookResponse.builder()
			.id(id)
			.title(title)
			.author(author)
			.description(description)
			.file(FileResponse.of(title, physicalPath))
			.build();
	}
}
