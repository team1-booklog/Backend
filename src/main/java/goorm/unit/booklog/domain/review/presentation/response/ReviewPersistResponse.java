package goorm.unit.booklog.domain.review.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record ReviewPersistResponse(
        @Schema(description = "독후감 아이디", example = "1", requiredMode = REQUIRED)
        Long id
)
{
    public static ReviewPersistResponse of(Long id) {
        return new ReviewPersistResponse(id);
    }
}
