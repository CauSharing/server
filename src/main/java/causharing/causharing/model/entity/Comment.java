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
@ToString(exclude = {"postId", "parentCommentId", "parentCommentId"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    private LocalDateTime commentDate;

    private String writer;

    @ManyToOne
    private Post postId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCommentId")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    private Comment parentCommentId;





}
