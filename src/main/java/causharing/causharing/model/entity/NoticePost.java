package causharing.causharing.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
@ToString(exclude = {"noticeId"})
public class NoticePost {

    @Id
    private Long noticePostId;

    private String title;

    private LocalDateTime noticeDate;

    private String content;

    @ManyToOne
    private Notice noticeId;

}
