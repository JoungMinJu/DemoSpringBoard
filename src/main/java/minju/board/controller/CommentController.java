package minju.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minju.board.domain.entity.Comment;
import minju.board.domain.repository.CommentRepository;
import minju.board.dto.ArticleDto;
import minju.board.dto.CommentDto;
import minju.board.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping()
public class CommentController {
    private final CommentService commentService;

    @PostMapping("article/{articleId}")
    public String article(@ModelAttribute CommentDto commentDto, @PathVariable Long articleId, Model model, @RequestParam String type) {
        commentService.addComment(commentDto,articleId);
        return "redirect:/article/{articleId}?type="+type;
    }
}
