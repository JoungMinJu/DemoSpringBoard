package minju.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minju.board.domain.entity.Comment;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Comment toComment(){
        Comment comment = Comment.builder()
                .content(content)
                .build();
        return comment;
    }

    @Builder
    public CommentDto(Long id, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
