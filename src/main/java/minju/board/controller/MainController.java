package minju.board.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minju.board.domain.entity.Category;
import minju.board.domain.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class MainController {
    // 카테고리 목록 보여주기
    private final CategoryRepository categoryRepository;

    @GetMapping
    public String main(Model model){
        // 모든 카테고리 보여주기
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "index.html";
    }
}
