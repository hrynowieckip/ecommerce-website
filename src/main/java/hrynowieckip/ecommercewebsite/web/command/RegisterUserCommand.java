package hrynowieckip.ecommercewebsite.web.command;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class RegisterUserCommand {
    @NotNull @Email
    private String username;
    @NotBlank @Size(min = 6, max = 24)
    private String password;
}
