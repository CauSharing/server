package causharing.causharing.model.request;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApiRequest {

    private String email;

    private String password;

    private String nickname;

    private String verificationCode;

    private String department;

    private String major;

    private String image;

    private String language;

    private boolean checkEmaile;


}