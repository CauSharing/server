package causharing.causharing.model.request;

import causharing.causharing.model.entity.MatchingRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

    private String message;

    private Long matchingRoomId;

    private String user;

    private LocalDateTime time;
}
