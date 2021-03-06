package minju.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minju.board.domain.entity.Article;
import minju.board.domain.repository.ArticleRepository;
import minju.board.domain.repository.CategoryRepository;
import minju.board.dto.ArticleDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public ArticleDto getArticle(Long id){
        Optional<Article> byId = articleRepository.findById(id);
        Article article = byId.get();
        ArticleDto articleDto = ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .sub_title(article.getSub_title())
                .content(article.getContent())
                .createdDate(article.getCreatedDate())
                .modifiedDate(article.getModifiedDate())
                .build();
        if(article.getCategory()!=null) articleDto.setCategory(article.getCategory());
        if(article.getComments()!=null) articleDto.setComments(article.getComments());
        return articleDto;
    }


    @Transactional
    public List<ArticleDto> getArticleList(){
        List<Article> articleEntities = articleRepository.findAll();
        List<ArticleDto> articleDtoList = new ArrayList<>();
        for (Article articleEntity : articleEntities) {
            ArticleDto articleDto = ArticleDto.builder()
                    .id(articleEntity.getId())
                    .title(articleEntity.getTitle())
                    .sub_title(articleEntity.getSub_title())
                    .content(articleEntity.getContent())
                    .createdDate(articleEntity.getCreatedDate())
                    .modifiedDate(articleEntity.getModifiedDate())
                    .build();
            articleDto.setCategory(articleEntity.getCategory());
            articleDtoList.add(articleDto);
        }
        // dto??? ??????????????????
        // ?????????????????????????????? ????????? ????????? dto??? ?????? ?????????
        return articleDtoList;
    }

    
    @Transactional
    public Article addArticle(ArticleDto articleDto, String type){
        articleDto.setCategory(categoryRepository.findByType(type));
        return articleRepository.save(articleDto.toArticle());
    }


    @Transactional
    public Long updateArticle(ArticleDto originArticle, ArticleDto articleDto){
        Article origin = articleRepository.getById(originArticle.getId());
        origin.setTitle(articleDto.getTitle());
        origin.setSub_title(articleDto.getSub_title());
        origin.setContent(articleDto.getContent());

        return origin.getId();
    }

    @Transactional
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
