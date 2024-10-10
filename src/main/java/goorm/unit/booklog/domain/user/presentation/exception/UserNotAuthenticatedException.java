package goorm.unit.booklog.domain.user.presentation.exception;


import goorm.unit.booklog.common.exception.CustomException;

import static goorm.unit.booklog.domain.user.presentation.exception.UserExceptionCode.USER_NOT_AUTHENTICATED;

public class UserNotAuthenticatedException extends CustomException {

    public UserNotAuthenticatedException() {
        super(USER_NOT_AUTHENTICATED);
    }
}
