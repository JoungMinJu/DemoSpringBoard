package minju.board.repository;

import minju.board.model.Article;
import minju.board.model.Board;
import minju.board.model.Comment;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CommentRepository {
    private static Map<Long, Comment> map = new HashMap<>();
    private static long sequence = 0L;

    // 저장
    public Comment save(Comment comment){
        comment.setId(++sequence);
        map.put(comment.getId(), comment);
        return comment;
    }
    // 하나 찾기
    public Comment findById(long id){
        return map.get(id);
    }
    // 전체 찾기
    public List<Comment> findAll(){
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
    // article Id 기반으로 모든 것 찾기
    public List<Comment> findByArticleId(long articleId){
        return map.values().stream()
                .filter(article -> article.getArticle_id()==articleId)
                .collect(Collectors.toList());
    }
    // update
    public void update(long id, Comment updateParam){
        Comment findComment = findById(id);
        findComment.setArticle_id(findComment.getArticle_id());
        findComment.setCreated_at(findComment.getCreated_at());
        findComment.setUpdated_at(LocalDate.now());
        findComment.setContent(updateParam.getContent());;
    }
}
