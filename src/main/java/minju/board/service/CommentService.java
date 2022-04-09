package minju.board.service;

import lombok.RequiredArgsConstructor;
import minju.board.domain.entity.Comment;
import minju.board.domain.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    // 댓글 CRUD
    public Comment getComment(Long id){
        Optional<Comment> optional = commentRepository.findById(id);
        return optional.get();
    }

    public List<Comment> getAllComment(){
        return commentRepository.findAll();}

    @Transactional
    public Comment addComment(Comment comment){
        return commentRepository.save(comment);
    }

    @Transactional
    public Long updateComment(Comment originComment, Comment comment){
        originComment.setContent(comment.getContent());
        return originComment.getId();
    }

    @Transactional
    public void deleteComment(Long id){
        Comment comment = getComment(id);
        commentRepository.delete(comment);
    }
}
