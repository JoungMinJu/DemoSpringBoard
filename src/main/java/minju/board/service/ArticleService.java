package minju.board.service;

import lombok.RequiredArgsConstructor;
import minju.board.model.Article;
import minju.board.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article getArticle(Long id) {
        Optional<Article> optional = articleRepository.findById(id);
        return optional.get();
    }

    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @Transactional
    public Article addArticle(Article article) {
        return articleRepository.save(article);
    }

    @Transactional
    public Long updateArticle(Article originArticle, Article article) {
        originArticle.setTitle(article.getTitle());
        originArticle.setSub_title(article.getSub_title());
        originArticle.setContent(article.getContent()); // dirty-checking 변경된 부분 알아서 저장
        return originArticle.getId();
    }

    @Transactional
    public void deletArticle(Long id) {
        Article article = getArticle(id);
        articleRepository.delete(article);
    }
}
