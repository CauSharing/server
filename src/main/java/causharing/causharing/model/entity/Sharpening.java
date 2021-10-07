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
@ToString(exclude = {"postId"})
public class Sharpening {

    @Id
    private Long SharpeningId;

    private String originalDescription;

    private String modifiedDescription;

    private int line;

    @ManyToOne
    private Post postId;
}
