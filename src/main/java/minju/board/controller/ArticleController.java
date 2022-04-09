package minju.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minju.board.domain.entity.Article;
import minju.board.dto.ArticleDto;
import minju.board.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        List<ArticleDto> articleList = articleService.getArticleList();
        model.addAttribute("articles", articleList);
         return "board/list.html";
    }


    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, Model model) {
        ArticleDto article = articleService.getArticle(articleId);
        model.addAttribute("article", article);
        return "article/detail";
    }

    @GetMapping("/add")
    public String addArticle(Model model) {
        model.addAttribute("article", new Article());
        return "article/addForm";
    }

//    @PostMapping("/add")
    // 사용자가 요청하는 값을 object로 만들어주는 파라미터내 @ModelAttribute
//    public String createArticle(@ModelAttribute Article article) {
//        Article createdArticle = articleService.addArticle(article);
//        return "redirect:/article/" + createdArticle.getId();
//    }

    @PostMapping("/add")
    public String createArticleV2(@ModelAttribute ArticleDto articleDto){
        articleService.addArticle(articleDto);
        return "redirect:/";
    }

    @GetMapping("/{articleId}/edit")
    public String editArticle(@PathVariable Long articleId, Model model) {
        ArticleDto article = articleService.getArticle(articleId);
        model.addAttribute("article", article);
        return "article/editForm";
    }

    @PostMapping("/{articleId}/edit")
    public String updateArticle(@PathVariable Long articleId, @ModelAttribute ArticleDto articleDto) {
        ArticleDto findArticle = articleService.getArticle(articleId);
        log.info(findArticle.getTitle());
        log.info(articleDto.getTitle());
        Long id = articleService.updateArticle(findArticle, articleDto);
        return "redirect:/article/" + id;
    }

    @GetMapping("/{articleID}/delete")
    public String deleteArticle(@PathVariable Long articleID) {
        articleService.deleteArticle(articleID);
        return "redirect:/article";
    }
}
