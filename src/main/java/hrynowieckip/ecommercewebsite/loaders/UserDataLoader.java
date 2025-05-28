package hrynowieckip.ecommercewebsite.loaders;

import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.domain.model.UserDetails;
import hrynowieckip.ecommercewebsite.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDataLoader implements DataLoader {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Transactional
    public void loadData() {
        User user = User.builder()
                .username("user@user")
                .password(passwordEncoder.encode("password"))
                .active(true)
                .roles(Set.of("ROLE_USER"))
                .build();
        user.setUserDetails(UserDetails.builder()
                .firstName("John")
                .lastName("Kowalski")
                .address("Jakaśtam")
                .zipCode("00-0000")
                .city("Kraków")
                .phoneNumber("000000000")
                .user(user)
                .build());

        userRepository.save(user);
        log.debug("Saved user with role_user: {}", user);

        User admin = User.builder()
                .username("admin@admin")
                .password(passwordEncoder.encode("password"))
                .active(true)
                .roles(Set.of("ROLE_USER", "ROLE_ADMIN"))
                .build();
        admin.setUserDetails(UserDetails.builder()
                .firstName("Tom")
                .lastName("Nowak")
                .address("Tamtam")
                .zipCode("00-0000")
                .city("Warszawa")
                .phoneNumber("000000000")
                .user(admin)
                .build());

        userRepository.save(admin);
        log.debug("Saved user with role_admin: {}", admin);
    }
}
