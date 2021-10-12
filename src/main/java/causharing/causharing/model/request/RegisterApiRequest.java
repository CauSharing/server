package causharing.causharing.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterApiRequest {

    private String email;

    private String password;

    private String confirmPw;

    private String nickname;

    private String department;

    private String major;

    private String language;



}
