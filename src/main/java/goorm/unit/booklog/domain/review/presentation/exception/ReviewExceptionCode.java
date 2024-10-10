package goorm.unit.booklog.domain.review.presentation.exception;

import goorm.unit.booklog.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ReviewExceptionCode implements ExceptionCode {
    REVIEW_NOT_FOUND(NOT_FOUND, "독후감을 찾을 수 없습니다."),;

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }
}
