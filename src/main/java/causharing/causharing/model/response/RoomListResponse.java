package causharing.causharing.model.response;

import causharing.causharing.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RoomListResponse {

    private Long matchingRoomId;

    private String matchingRoomImage;

    private List<UserMailAndName> userList;





}
