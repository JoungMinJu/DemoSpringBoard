package minju.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Article extends BaseEntityTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String sub_title;
    private String content;

    public Article() {
    }

    public Article(String title, String sub_title, String content) {
        this.title = title;
        this.sub_title = sub_title;
        this.content = content;
    }

    // 좋아요 개수(생략)
    // 싫어요 개수(생략)

    // board와 article은 1:n 관계
    // 게시글과 카테고리는 N:1
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "article")
    List<Comment> comments = new ArrayList<>();
}
