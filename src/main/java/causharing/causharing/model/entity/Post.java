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
@ToString(exclude = {"matchingRoomId", "userEmail", "postId"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;

    private LocalDateTime postDate;

    private String content;

    @ManyToOne
    private MatchingRoom matchingRoomId;

    @ManyToOne
    private User userEmail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postId",cascade =CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postId")
    private List<Sharpening> sharpeningList = new ArrayList<>();
}
