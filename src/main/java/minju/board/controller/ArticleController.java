package minju.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minju.board.domain.entity.Article;
import minju.board.domain.entity.Category;
import minju.board.domain.entity.Comment;
import minju.board.dto.ArticleDto;
import minju.board.dto.CommentDto;
import minju.board.service.ArticleService;
import minju.board.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    // 전체 게시글 다 보여주기
    @GetMapping
    public String all(Model model, @RequestParam String type) {
        List<ArticleDto> articleList;
        try{ articleList= articleService.getArticleList()
                .stream()
                .filter(articleDto -> articleDto.getCategory().getType().equals(type))
                .collect(Collectors.toList());}
        catch(Exception e){
            articleList = new ArrayList<>();
        }

        model.addAttribute("articles", articleList);
        model.addAttribute("type", type);
         return "article/list.html";
    }


    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, Model model, @RequestParam String type) {
        ArticleDto article = articleService.getArticle(articleId);
        List<CommentDto> commentList;
        try{
            commentList =commentService.getAllComment()
                    .stream()
                    .filter(commentDto -> commentDto.getArticle().getId().equals(articleId))
                    .collect(Collectors.toList());
        }catch(Exception e){
            commentList = new ArrayList<>();
        }
        model.addAttribute("article", article);
        model.addAttribute("type",type);
        model.addAttribute("comments",commentList);
        return "article/detail";
    }

    @GetMapping("/add")
    public String addArticle(Model model, @RequestParam String type) {
        model.addAttribute("article", new ArticleDto());
        return "article/addForm";
    }

//    @PostMapping("/add")
    // 사용자가 요청하는 값을 object로 만들어주는 파라미터내 @ModelAttribute
//    public String createArticle(@ModelAttribute Article article) {
//        Article createdArticle = articleService.addArticle(article);
//        return "redirect:/article/" + createdArticle.getId();
//    }

    @PostMapping("/add")
    public String createArticle(@ModelAttribute ArticleDto articleDto, @RequestParam String type){
        articleService.addArticle(articleDto, type);
        return "redirect:/article?type="+type;
    }

    @GetMapping("/{articleId}/edit")
    public String editArticle(@PathVariable Long articleId, Model model) {
        ArticleDto article = articleService.getArticle(articleId);
        model.addAttribute("article", article);
        return "article/editForm";
    }

    @PostMapping("/{articleId}/edit")
    public String updateArticle(@PathVariable Long articleId, @ModelAttribute ArticleDto articleDto, @RequestParam String type) {
        ArticleDto findArticle = articleService.getArticle(articleId);
        Long id = articleService.updateArticle(findArticle, articleDto);
        return "redirect:/article/" + id+"?type="+type;
    }

    @GetMapping("/{articleID}/delete")
    public String deleteArticle(@PathVariable Long articleID, @RequestParam String type) {
        articleService.deleteArticle(articleID);
        return "redirect:/article?type="+type;
    }
}
