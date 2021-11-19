package causharing.causharing.model.request;

import causharing.causharing.model.EditedData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditedDataRequest {

    private Long postId;

    List<EditedData> editedDataList = new ArrayList<>();

}
