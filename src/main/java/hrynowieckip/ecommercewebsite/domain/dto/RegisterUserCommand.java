package hrynowieckip.ecommercewebsite.domain.dto;

import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
