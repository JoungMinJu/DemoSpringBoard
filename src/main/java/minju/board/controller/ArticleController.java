package minju.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minju.board.model.Article;
import minju.board.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    // 전체 게시글 다 보여주기
    @GetMapping
    public String all(Model model) {
        List<Article> articles = articleService.getAllArticle();
        model.addAttribute("articles", articles);
        return "article/article";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, Model model) {
        Article article = articleService.getArticle(articleId);
        model.addAttribute("article", article);
        return "article/detail";
    }

    @GetMapping("/add")
    public String addArticle(Model model) {
        model.addAttribute("article", new Article());
        return "article/addForm";
    }

    @PostMapping("/add")
    public String createArticle(@ModelAttribute Article article) {
        Article createdArticle = articleService.addArticle(article);
        return "redirect:/article/" + createdArticle.getId();
    }

    @GetMapping("/{articleId}/edit")
    public String editArticle(@PathVariable Long articleId, Model model) {
        Article article = articleService.getArticle(articleId);
        model.addAttribute("article", article);
        return "article/editForm";
    }

    @PostMapping("/{articleId}/edit")
    public String updateArticle(@PathVariable Long articleId, @ModelAttribute Article article) {
        Article findArticle = articleService.getArticle(articleId);
        Long id = articleService.updateArticle(findArticle, article);
        return "redirect:/article/" + id;
    }

    @GetMapping("/{articleID}/delete")
    public String deleteArticle(@PathVariable Long articleID) {
        articleService.deletArticle(articleID);
        return "redirect:/article";
    }
}
