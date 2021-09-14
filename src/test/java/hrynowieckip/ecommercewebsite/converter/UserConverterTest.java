package hrynowieckip.ecommercewebsite.converter;

import hrynowieckip.ecommercewebsite.data.user.UserSummary;
import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.domain.model.UserDetails;
import hrynowieckip.ecommercewebsite.web.command.EditUserCommand;
import hrynowieckip.ecommercewebsite.web.command.RegisterUserCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserConverterTest {
    private UserConverter userConverter;

    @BeforeEach
    public void setup(){
        userConverter = new UserConverter();
    }

    @Test
    void fromTest() {
        //Given
        User expected = User.builder()
                .username("user@user")
                .password("pass")
                .build();

        RegisterUserCommand registerUserCommand = RegisterUserCommand.builder()
                .username("user@user")
                .password("pass")
                .build();
        //When
        User result = userConverter.from(registerUserCommand);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void toUserSummaryTest() {
        //Given
        UserSummary expected = UserSummary.builder()
                .username("user@user")
                .firstName("Tom")
                .lastName("Kowalski")
                .address("Address")
                .zipCode("000000")
                .city("City")
                .phoneNumber("000000000")
                .build();

        UserDetails userDetails = UserDetails.builder()
                .firstName("Tom")
                .lastName("Kowalski")
                .address("Address")
                .zipCode("000000")
                .city("City")
                .phoneNumber("000000000")
                .build();

        User user = User.builder()
                .username("user@user")
                .userDetails(userDetails)
                .build();
        //When
        UserSummary result = userConverter.toUserSummary(user);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void testFromTest() {
        //Given
        UserDetails userDetails = UserDetails.builder()
                .firstName("Tim")
                .lastName("Nowak")
                .address("Address")
                .zipCode("000000")
                .city("City")
                .phoneNumber("000000000")
                .build();

        User expected = User.builder()
                .username("user@user")
                .userDetails(userDetails)
                .build();

        EditUserCommand editUserCommand = EditUserCommand.builder()
                .firstName("Tim")
                .lastName("Nowak")
                .address("Address")
                .zipCode("000000")
                .city("City")
                .phoneNumber("000000000")
                .build();

        UserDetails userDetailsToEdit = UserDetails.builder()
                .firstName("Tim")
                .lastName("Nowak")
                .address("Address")
                .zipCode("000000")
                .city("City")
                .phoneNumber("000000000")
                .build();

        User userToEdit = User.builder()
                .username("user@user")
                .userDetails(userDetailsToEdit)
                .build();

        //When
        User result = userConverter.from(editUserCommand, userToEdit);
        //Then
        assertEquals(expected, result);
    }
}