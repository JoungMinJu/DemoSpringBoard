package minju.board.service;

import lombok.RequiredArgsConstructor;
import minju.board.domain.entity.Article;
import minju.board.domain.entity.Comment;
import minju.board.domain.repository.ArticleRepository;
import minju.board.domain.repository.CommentRepository;
import minju.board.dto.CommentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    // 댓글 CRUD
    public CommentDto getComment(Long id){
        Optional<Comment> optional = commentRepository.findById(id);
        Comment comment = optional.get();
        CommentDto commentDto = CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .build();
        return commentDto;
    }

    public List<CommentDto> getAllComment(){
        List<Comment> all = commentRepository.findAll();
        List<CommentDto> dtoList = new ArrayList<>();
        for (Comment comment : all) {
            CommentDto commentDto = CommentDto.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .createdDate(comment.getCreatedDate())
                    .modifiedDate(comment.getModifiedDate())
                    .build();
            commentDto.setArticle(comment.getArticle());
            dtoList.add(commentDto);
        }
        return dtoList;}

    @Transactional
    public Comment addComment(CommentDto commentDto, Long articleId){
        Optional<Article> byId = articleRepository.findById(articleId);
        Article article = byId.get();
        commentDto.setArticle(article);
        return commentRepository.save(commentDto.toComment());
    }

    @Transactional
    public Long updateComment(CommentDto originComment, CommentDto comment){
        originComment.setContent(comment.getContent());
        return originComment.getId();
    }

    @Transactional
    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }
}
