package framework.lombok;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class Lombok_AddUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}