package causharing.causharing.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostRequest {

    private String title;

    private LocalDateTime postDate;

    private String content;

    private Long matchingRoomId;

}
