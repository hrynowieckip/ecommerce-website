package hrynowieckip.ecommercewebsite.service;

import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.domain.model.UserDetails;
import hrynowieckip.ecommercewebsite.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AdminService adminService;

    @Test
    public void addAdminRoleToUserTest() {
        //Given
        long userId = 11L;
        UserDetails userDetails = UserDetails.builder()
                .firstName("Tom")
                .lastName("Nowak")
                .address("Address")
                .zipCode("00000")
                .city("City")
                .phoneNumber("000000000")
                .build();
        User user = User.builder()
                .id(11L)
                .username("user@user")
                .password("pass")
                .roles(new HashSet<>(Arrays.asList("ROLE_USER")))
                .userDetails(userDetails)
                .build();

        //When
        Mockito.when(userRepository.getByUsername("user@user")).thenReturn(user);

        Long result = adminService.addAdminRoleToUser("user@user");

        //Then
        assertThat(result)
                .isNotNull()
                .isPositive()
                .isEqualTo(userId);

    }

}