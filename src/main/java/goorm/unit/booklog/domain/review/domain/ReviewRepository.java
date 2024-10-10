package goorm.unit.booklog.domain.review.domain;

import java.util.Optional;

public interface ReviewRepository {

    Review save(Review review);

    Optional<Review> findById(Long id);

}
