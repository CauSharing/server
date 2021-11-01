package causharing.causharing.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import java.util.*;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
@ToString(exclude = {"matchingRoomId"})
public class MatchingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingRoomId;

    private String matchingRoomImage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingRoomId")
    private List<Matching> matchingList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingRoomId")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingRoomId")
    private List<Notice> noticeList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingRoomId")
    private List<Chat> chatList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingRoomId")
    private List<Tag> tagList = new ArrayList<>();


}
