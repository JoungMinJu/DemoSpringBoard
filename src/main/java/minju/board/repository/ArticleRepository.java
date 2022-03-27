package minju.board.repository;

import minju.board.model.Article;
import minju.board.model.Board;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ArticleRepository {
    private static Map<Long, Article> map = new HashMap<>();
    private static long sequence = 0L;

    // 저장
    public Article save(Article article){
        article.setId(++sequence);
        map.put(article.getId(), article);
        return article;
    }
    // 하나 찾기
    public Article findById(long id){
        return map.get(id);
    }
    // 전체 찾기
    public List<Article> findAll(){
        return new ArrayList<>(map.values());
    }
    // 삭제하기
    public void delete(long id){
        map.remove(id);
    }
    // clear
    public void clear(){
        map.clear();
    }
    // board Id 기반으로 모든 것 찾기
    public List<Article> findByBoardId(long boardId){
        return map.values().stream()
                .filter(article -> article.getBoard_id()==boardId)
                .collect(Collectors.toList());
    }
    // update
    public void update(Long id, Article updateParam){
        Article findArticle = findById(id);
        findArticle.setBoard_id(findArticle.getBoard_id());
        findArticle.setCreated_at(findArticle.getCreated_at());
        findArticle.setTitle(updateParam.getTitle());
        findArticle.setSub_title(updateParam.getSub_title());
        findArticle.setContent(updateParam.getContent());
        findArticle.setUpdated_at(LocalDate.now());
    }

}
