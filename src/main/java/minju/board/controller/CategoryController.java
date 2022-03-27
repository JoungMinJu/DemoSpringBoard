package minju.board.controller;

import minju.board.model.Article;
import minju.board.model.Category;
import minju.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/category")
public class CategoryController {

//    private final BoardRepository boardRepository;
//    private final ArticleRepository articleRepository;
//    // board list 보여주기
//
//    // board 페이지 들어가기
//    @GetMapping
//    public String boards(Model model){
//        List<Board> boards = boardRepository.findAll();
//        model.addAttribute("boards", boards);
//        return "boards";
//    }
//    // board 페이지 보여주기
//    @GetMapping("/{categoryId}")
//    public String board(@PathVariable long categoryId, Model model){
//        Category category = boardRepository.findById(boardId);
//        model.addAttribute("board", board);
//        List<Article> articles = articleRepository.findByBoardId(boardId);
//        model.addAttribute("articles", articles);
//        return "board";
//    }


}
