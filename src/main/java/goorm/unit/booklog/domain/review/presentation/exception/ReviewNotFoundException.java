package goorm.unit.booklog.domain.review.presentation.exception;

import goorm.unit.booklog.common.exception.CustomException;

import static goorm.unit.booklog.domain.review.presentation.exception.ReviewExceptionCode.REVIEW_NOT_FOUND;

public class ReviewNotFoundException extends CustomException {
    public ReviewNotFoundException() {
        super(REVIEW_NOT_FOUND);
    }
}
