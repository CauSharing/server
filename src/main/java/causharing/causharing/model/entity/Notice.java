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
public class Notice {

    @Id
    private Long noticeId;

    private String categoryName;

    @ManyToOne
    private MatchingRoom matchingRoomId;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "noticeId")
    private List<NoticePost> noticePostList = new ArrayList<>();
}
