package causharing.causharing.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = {"sharpeningId"})
public class EditedData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long editedDataId;

    private int line;

    private String content;

    @ManyToOne
    private Sharpening sharpeningId;
}
