package minju.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Comment extends BaseEntityTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    public Comment() {
    }

    private LocalDate created_at;
    private LocalDate updated_at;

    // article과 comment는 1:n 관계
    // N + 1
    // article -> sql
    // comment * n -> sql
    @ManyToOne(fetch = FetchType.LAZY) // EAGER(바로 갖고오려고, N + 1), LAZY(필요할 때) -> sql을 어떻게 갖고 올건지
    private Article article;
}
