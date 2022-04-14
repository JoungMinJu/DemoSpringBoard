package minju.board.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="article")
public class Article extends BaseEntityTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=100, nullable = false)
    private String title;
    @Column(length=100, nullable = false)
    private String sub_title;
    @Column(columnDefinition = "TEXT", nullable=false)
    private String content;

    @Builder
    public Article(Long id,String title, String sub_title, String content) {
        this.id = id;
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


    @OneToMany(mappedBy = "article" ,cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.EAGER)
    List<Comment> comments = new ArrayList<>();
}
