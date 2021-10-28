package causharing.causharing.model.response;

import causharing.causharing.model.request.InvitationList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class InvitationListResponse {
    private List<InvitationList> invitationList;
}
