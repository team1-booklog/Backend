package goorm.unit.booklog.domain.review.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import goorm.unit.booklog.domain.review.domain.Review;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ReviewResponse(
        @Schema(
                description = "게시물 썸네일",
                example = "https://example.domain.com/files/example.jpg",
                requiredMode = NOT_REQUIRED)
        String thumbImg,

        @Schema(description = "독후감 제목", example = "해리포터를 읽고", requiredMode = REQUIRED)
        String title,

        @Schema(description = "작성자 이름", example = "홍길동", requiredMode = REQUIRED)
        String name,

        @Schema(description = "독후감 본문", example = "이번 기회에 해리포터라는 책을 읽게 되었다.", requiredMode = NOT_REQUIRED)
        String content,

        @Schema(description = "책 제목", example = "해리포터", requiredMode = NOT_REQUIRED)
        String book_name,

        @Schema(description = "작성 일자", example = "-", requiredMode = NOT_REQUIRED)
        LocalDateTime createdAt,

        @Schema(description = "수정 일자", example = "-", requiredMode = NOT_REQUIRED)
        LocalDateTime updatedAt
) {

    public static ReviewResponse of(Review review) {
        return new ReviewResponse(
                review.getFile().getPhysicalPath(),
                review.getTitle(),
                review.getUser().getName(),
                review.getContent(),
                review.getBook().getTitle(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
}
