package causharing.causharing.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRequest {

    private Long matchingRoomId;

    private String matchingRoomImage;

    private String matchingRoomName;

}
