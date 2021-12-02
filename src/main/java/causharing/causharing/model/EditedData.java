package causharing.causharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditedData {

    @JsonIgnore
    private Long EditedDataId;

    private int line;

    private String content;

    private String writer;
}
