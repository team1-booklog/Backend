package goorm.unit.booklog.domain.user.application;

import goorm.unit.booklog.domain.user.domain.User;
import goorm.unit.booklog.domain.user.presentation.exception.UserNotAuthenticatedException;
import goorm.unit.booklog.domain.user.presentation.request.UserCreateRequest;
import goorm.unit.booklog.domain.user.domain.UserRepository;
import goorm.unit.booklog.domain.user.presentation.exception.UserIdDuplicatedException;
import goorm.unit.booklog.domain.user.presentation.exception.UserNotFoundException;
import goorm.unit.booklog.domain.user.presentation.response.UserPersistResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public UserPersistResponse createUser(UserCreateRequest request) {
        User user = User.create(
                request.id(),
                request.name(),
                bCryptPasswordEncoder.encode(request.password())
        );
        String id = userRepository.save(user).getId();
        return UserPersistResponse.of(id);
    }

    public void validateIdDuplication (String id) {
        if(userRepository.existsById(id)) {
            throw new UserIdDuplicatedException();
        }
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User me() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String id= ((UserDetails)principal).getUsername();
            return getUserById(id);
        } catch (Exception e) {
            throw new UserNotAuthenticatedException();
        }
    }

}

