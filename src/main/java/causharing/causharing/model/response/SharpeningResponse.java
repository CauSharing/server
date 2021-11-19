package causharing.causharing.model.response;

import causharing.causharing.model.EditedData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SharpeningResponse {

    private Long postId;

    private List<EditedData> editedDataList;

}
