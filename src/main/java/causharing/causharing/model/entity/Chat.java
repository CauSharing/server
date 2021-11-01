package causharing.causharing.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    private String message;

    private LocalDateTime chatDate;

    @ManyToOne
    private MatchingRoom matchingRoomId;

    @ManyToOne
    private User userEmail;
}
