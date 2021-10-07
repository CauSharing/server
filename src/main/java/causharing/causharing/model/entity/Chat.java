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
@ToString(exclude = {"matchingRoomId", "userEmail"})
public class Chat {

    @Id
    private Long chatId;

    private String message;

    private LocalDateTime chatDate;

    @ManyToOne
    private MatchingRoom matchingRoomId;

    @ManyToOne
    private User userEmail;
}
