package minju.board.dto;

import lombok.*;
import minju.board.domain.entity.Article;
import minju.board.domain.entity.Category;
import minju.board.domain.entity.Comment;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String sub_title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Category category;
    private List<Comment> comments;

    public Article toArticle(){
        Article article = Article.builder()
                .id(id)
                .title(title)
                .sub_title(sub_title)
                .content(content)
                .build();
        article.setCategory(category);
        return article;
    }
    @Builder
    public ArticleDto(Long id, String title, String sub_title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.sub_title = sub_title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
