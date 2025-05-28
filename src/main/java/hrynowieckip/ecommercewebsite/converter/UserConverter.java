package hrynowieckip.ecommercewebsite.converter;

import hrynowieckip.ecommercewebsite.domain.dto.UserSummary;
import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.domain.model.UserDetails;
import hrynowieckip.ecommercewebsite.domain.dto.EditUserCommand;
import hrynowieckip.ecommercewebsite.domain.dto.RegisterUserCommand;
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

    public User from(EditUserCommand editUserCommand, User userToEdit) {
        UserDetails userDetails = userToEdit.getUserDetails();
        userDetails.setFirstName(editUserCommand.getFirstName());
        userDetails.setLastName(editUserCommand.getLastName());
        userDetails.setAddress(editUserCommand.getAddress());
        userDetails.setZipCode(editUserCommand.getZipCode());
        userDetails.setCity(editUserCommand.getCity());
        userDetails.setPhoneNumber(editUserCommand.getPhoneNumber());
        return userToEdit;
    }
}
