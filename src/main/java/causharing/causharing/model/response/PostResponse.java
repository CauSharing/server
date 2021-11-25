package causharing.causharing.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    private Long postId;

    private String title;

    private LocalDateTime postDate;

    @Column(length = 100000)
    private String content;

    private Long matchingRoomId;

    private String userNickname;

    private String userEmail;

}
