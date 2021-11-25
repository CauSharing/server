package causharing.causharing.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentListResponse {

    private String content;

    private Long commentId;

    private LocalDateTime commentDate;

    private String nickname;

    private String email;

    private List<CommentListResponse> childComment;

}
