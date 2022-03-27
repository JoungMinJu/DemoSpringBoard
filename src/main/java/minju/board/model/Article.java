package minju.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Article {
    private long id;
    private String title;
    private String sub_title;
    private String content;

    public Article() {
    }

    public Article(String title, String sub_title, String content) {
        this.title = title;
        this.sub_title = sub_title;
        this.content = content;
    }

    // 좋아요 개수(생략)
    // 싫어요 개수(생략)
    private LocalDate created_at;
    private LocalDate  updated_at;
    // board와 article은 1:n 관계
    private long board_id;
}
