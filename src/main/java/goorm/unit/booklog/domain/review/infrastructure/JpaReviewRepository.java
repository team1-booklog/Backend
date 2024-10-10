package goorm.unit.booklog.domain.review.infrastructure;

import goorm.unit.booklog.domain.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReviewRepository extends JpaRepository<Review,Long> {
}
