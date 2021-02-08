package hrynowieckip.ecommercewebsite.converter;

import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.web.command.RegisterUserCommand;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User from(RegisterUserCommand registerUserCommand) {
        return User.builder()
                .username(registerUserCommand.getUsername())
                .password(registerUserCommand.getPassword())
                .build();

    }
}
