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
public class ChatResponse {

    private String message;

    private LocalDateTime chatDate;

    private String nickname;

    private String email;

    private String image;
}
