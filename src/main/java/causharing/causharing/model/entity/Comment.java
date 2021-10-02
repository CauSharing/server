package causharing.causharing.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import java.util.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class Comment {

    @Id
    private Long commentId;

    private String content;

    private LocalDateTime commentDate;

    @ManyToOne
    private Post postId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCommentId")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    private Comment parentCommentId;

}
