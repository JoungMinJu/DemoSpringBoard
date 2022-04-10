package minju.board;

import lombok.RequiredArgsConstructor;
import minju.board.domain.entity.Article;
import minju.board.domain.entity.Category;
import minju.board.domain.entity.Comment;
import minju.board.domain.repository.ArticleRepository;
import minju.board.domain.repository.CategoryRepository;
import minju.board.domain.repository.CommentRepository;
import minju.board.dto.ArticleDto;
import minju.board.dto.CommentDto;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TesDataInit {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;

    // 게시판 미리 추가
    @PostConstruct
    public void init(){

        // 카테고리 미리 추가
        categoryRepository.save(Category.builder()
                .type("free")
                .boardName("자유게시판")
                .build());
        categoryRepository.save(Category.builder()
                .type("info")
                .boardName("정보게시판")
                .build());
        Article article = ArticleDto.builder()
                .title("안녕하세요")
                .sub_title("어쩌구")
                .content("합니다")
                .build().toArticle();
        article.setCategory(categoryRepository.findByType("free"));
        articleRepository.save(article);
        Comment comment = Comment.builder()
                .content("댓글입니다.")
                .build();
        comment.setArticle(article);
        commentRepository.save(comment);
    }
}
