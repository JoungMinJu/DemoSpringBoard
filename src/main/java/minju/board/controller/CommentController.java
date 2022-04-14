package minju.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minju.board.domain.entity.Comment;
import minju.board.domain.repository.CommentRepository;
import minju.board.dto.ArticleDto;
import minju.board.dto.CommentDto;
import minju.board.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/article/{articleId}")
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    public String article(@ModelAttribute CommentDto commentDto, @PathVariable Long articleId, Model model, @RequestParam String type) {
        commentService.addComment(commentDto,articleId);
        return "redirect:/article/{articleId}?type="+type;
    }

    // 석환오빠의 도움~.......
    @GetMapping("/comment/edit/{commentId}")
    public String editForm( @PathVariable Long commentId, Model model){
        CommentDto comment = commentService.getComment(commentId);
        model.addAttribute("comment", comment);
        return "comment/editForm";
    }

    @PostMapping("/comment/edit/{commentId}")
    public String edit(@PathVariable Long articleId,@PathVariable Long commentId,@ModelAttribute CommentDto commentDto, @RequestParam String type){
        CommentDto comment = commentService.getComment(commentId);
         commentService.updateComment(comment, commentDto);
        return "redirect:/article/" + articleId + "?type="+type;
    }

    @GetMapping("/comment/delete/{commentId}")
    public String delete(@PathVariable Long commentId, @PathVariable Long articleId, @RequestParam String type){
        commentService.deleteComment(commentId);
        return "redirect:/article/" + articleId +"?type="+type;
    }


//"@{'/article'+${articleId}+'/comment/update/'+${comment.id}(type=${type})}"
}
