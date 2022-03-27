package minju.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor // JPA는 기본적으로 빈 생성자가 있어야 됨 (public이든 protected)
@Entity
public class Category extends BaseEntityTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String boardName;

    public Category(String type, String boardName) {
        this.type = type;
        this.boardName = boardName;
    }

    @OneToMany(mappedBy = "category") // 1 : N, mappedBy는 상대쪽 필드명 써주면 됨
    List<Article> articles = new ArrayList<>();

}
