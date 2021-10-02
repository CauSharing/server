package causharing.causharing.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class Sharpening {

    @Id
    private Long SharpeningId;

    private String originalDescription;

    private String modifiedDescription;

    private int line;
}
