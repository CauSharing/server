package causharing.causharing.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class Matching {

    @Id
    private Long MatchingId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Matching matchingRoomId;


}
