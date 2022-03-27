package minju.board.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minju.board.model.Comment;
import minju.board.repository.ArticleRepository;
import minju.board.repository.BoardRepository;
import minju.board.repository.CommentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("boards/{boardId}/articles/{articleId}")
@Slf4j
public class CommentController {

    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/add")
    public String addForm(@PathVariable long articleId, @PathVariable long boardId, Model model){
        model.addAttribute("comment", new Comment());
        model.addAttribute("articleId", articleId);
        model.addAttribute("boardId", boardId);
        return "article/comment/addForm";
    }
    @PostMapping("/add")
    public String addComment(@PathVariable long articleId, @ModelAttribute Comment comment){
        comment.setCreated_at(LocalDate.now());
        comment.setUpdated_at(LocalDate.now());
        comment.setArticle_id(articleId);
        Comment saveComment = commentRepository.save(comment);
        return "redirect:/boards/{boardId}/articles/{articleId}";
    }
    // 수정
    @GetMapping("/{commentId}/edit")
    public String editForm(@PathVariable long commentId,@PathVariable long boardId, @PathVariable long articleId, Model model){
        Comment comment = commentRepository.findById(commentId);
        model.addAttribute("comment", comment);
        model.addAttribute("boardId", boardId);
        model.addAttribute("articleId", articleId);
        return "article/comment/editForm";
    }
    @PostMapping("/{commentId}/edit")
    public String editComment(@PathVariable long commentId, @ModelAttribute Comment comment){
        commentRepository.update(commentId, comment);
        return "redirect:/boards/{boardId}/articles/{articleId}";
    }
}
