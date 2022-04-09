package minju.board.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment extends BaseEntityTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @Builder
    public Comment(String content) {
        this.content = content;
    }





    // article과 comment는 1:n 관계
    // N + 1
    // article -> sql
    // comment * n -> sql
    @ManyToOne(fetch = FetchType.LAZY) // EAGER(바로 갖고오려고, N + 1), LAZY(필요할 때) -> sql을 어떻게 갖고 올건지
    private Article article;
}
