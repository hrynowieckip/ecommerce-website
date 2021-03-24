package hrynowieckip.ecommercewebsite.service;

import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public Long addAdminRoleToUser(String username) {
        User user = userRepository.getByUsername(username);
        Set<String> roles = user.getRoles();
        roles.add("ROLE_ADMIN");
        user.setRoles(roles);
        log.debug("Role \"admin\" was added for user: {}", user);
        return user.getId();
    }

    public Long deleteOrMakeSureThatUserDontHaveAdminRole(String username) {
        User user = userRepository.getByUsername(username);
        Set<String> roles = user.getRoles();
        if (roles.contains("ROLE_ADMIN")) {
            roles.remove("ROLE_ADMIN");
            log.debug("Role \"admin\" was deleted for user: {}", user);
        }
        return user.getId();
    }
}
