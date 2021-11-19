package causharing.causharing.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
@ToString(exclude = {"postId, sharpeningId"})
public class Sharpening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SharpeningId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sharpeningId")
    private List<EditedData> editedData = new ArrayList<>();

    private String writer;

    @ManyToOne
    private Post postId;
}
