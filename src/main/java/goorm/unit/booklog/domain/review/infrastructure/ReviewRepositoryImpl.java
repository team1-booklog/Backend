package goorm.unit.booklog.domain.review.infrastructure;

import goorm.unit.booklog.domain.review.domain.Review;
import goorm.unit.booklog.domain.review.domain.ReviewRepository;
import goorm.unit.booklog.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    private final JpaReviewRepository jpaReviewRepository;

    @Override
    public Review save(Review review) {
        return jpaReviewRepository.save(review);
    }

    @Override
    public Optional<Review> findById(Long id) {
        return jpaReviewRepository.findById(id);
    }

}
