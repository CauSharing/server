package causharing.causharing.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagListResponse {

    private List<TagResponse> tagResponseList;

}
