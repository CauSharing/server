package causharing.causharing.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    private Long postId;

    private String title;

    private LocalDateTime postDate;

    private String postImage;

    private String content;

    private Long matchingRoomId;

    private String userNickname;

}
