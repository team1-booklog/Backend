package goorm.unit.booklog.domain.review.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import goorm.unit.booklog.common.domain.BaseTimeEntity;
import goorm.unit.booklog.domain.book.domain.Book;
import goorm.unit.booklog.domain.file.domain.File;
import goorm.unit.booklog.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;

    @Column(nullable = false, length = 10)
    private String status = "ACTIVE";

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id") // 외래 키 설정
    private User user;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    public static Review create( String title, String content, File file,User user, Book book ) {
        return Review.builder()
                .title(title)
                .content(content)
                .file(file)
                .user(user)
                .status("ACTIVE")
                .book(book)
                .build();
    }
}
