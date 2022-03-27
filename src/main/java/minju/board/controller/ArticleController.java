package minju.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minju.board.model.Article;
import minju.board.model.Board;
import minju.board.model.Comment;
import minju.board.repository.ArticleRepository;
import minju.board.repository.BoardRepository;
import minju.board.repository.CommentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("boards/{boardId}")
@Slf4j
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/add")
    public String addForm(@PathVariable("boardId") long boardId, Model model){
        model.addAttribute("article", new Article());
        log.info("{}", boardId);
        model.addAttribute("boardId", boardId);
        return "article/addForm";
    }

    @PostMapping("/add")
    public String addArticle(@PathVariable("boardId") long boardId, @ModelAttribute Article article){
        article.setCreated_at(LocalDate.now());
        article.setUpdated_at(LocalDate.now());
        article.setBoard_id(boardId);
        Article saveArticle = articleRepository.save(article);
        log.info("article={}", article);
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/articles/{articleId}")
    public String article(@PathVariable long articleId, @PathVariable long boardId,  Model model){
        Article article = articleRepository.findById(articleId);
        model.addAttribute("article", article);
        Board board = boardRepository.findById(boardId);
        model.addAttribute("boardId", board.getId());
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        model.addAttribute("comments", comments);
        return "article/article";
    }

    // 수정
    @GetMapping("/articles/{articleId}/edit")
    public String editForm(@PathVariable long articleId, Model model){
        Article article = articleRepository.findById(articleId);
        model.addAttribute("article", article);
        return "article/editForm";
    }

    @PostMapping("/articles/{articleId}/edit")
    public String edit2(@PathVariable Long articleId, @ModelAttribute Article article) {
        articleRepository.update(articleId, article);
        return "redirect:";
    }


}