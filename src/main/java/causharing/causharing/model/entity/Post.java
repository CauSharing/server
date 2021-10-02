package causharing.causharing.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import java.util.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class Post {

    @Id
    private Long postId;

    private String title;

    private LocalDateTime postDate;

    private String postImage;

    private String content;

    @ManyToOne
    private MatchingRoom matchingRoomId;

    @ManyToOne
    private User userEmail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postId")
    private List<Comment> commentList = new ArrayList<>();
}
