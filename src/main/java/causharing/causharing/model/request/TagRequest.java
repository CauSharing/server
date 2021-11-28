package causharing.causharing.model.request;

import causharing.causharing.model.entity.MatchingRoom;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagRequest {

    private String tagName;

    @ApiModelProperty(example = "2021-10-23")
    private String startDate;

    @ApiModelProperty(example = "2021-10-29")
    private String endDate;

    private String rgb;

    private Long matchingRoomId;
}
