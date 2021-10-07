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
@ToString(exclude = {"matchingRoomId"})
public class Tag {

    @Id
    private Long tagId;

    private String startDate;

    private String endDate;

    private String rgb;

    private  String tagName;

    @ManyToOne
    private MatchingRoom matchingRoomId;
}