package minju.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private long id;
    private String type;
    private String boardName;

    public Board(String type, String boardName) {
        this.type = type;
        this.boardName = boardName;
    }
}
