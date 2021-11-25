package causharing.causharing.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditedData {

    private Long EditedDataId;

    private int line;

    private String content;

    private String writer;
}
