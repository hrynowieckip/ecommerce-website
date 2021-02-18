package hrynowieckip.ecommercewebsite.converter;

import hrynowieckip.ecommercewebsite.data.user.UserSummary;
import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.domain.model.UserDetails;
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

    public UserSummary toUserSummary(User user) {
        UserDetails userDetails= user.getUserDetails();
        return UserSummary.builder()
                .username(user.getUsername())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .address(userDetails.getAddress())
                .zipCode(userDetails.getZipCode())
                .city(userDetails.getCity())
                .phoneNumber(userDetails.getPhoneNumber())
                .build();
    }
}
