package hrynowieckip.ecommercewebsite.configuration.security;

import hrynowieckip.ecommercewebsite.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Getting a user named: {}", username);
        return userRepository.findByUsername(username).map(
                user ->
                    new User(
                            user.getUsername(),
                            user.getPassword(),
                            user.getActive(),
                            true,
                            true,
                            true,
                            user.getRoles().stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toSet())
                    )
                ).orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
