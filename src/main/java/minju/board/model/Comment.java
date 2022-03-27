package minju.board.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class Comment {
    private long id;
    private String content;

    public Comment() {
    }

    private LocalDate created_at;
    private LocalDate updated_at;
    // article과 comment는 1:n 관계
    private long article_id;
}
