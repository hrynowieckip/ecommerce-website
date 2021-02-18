package hrynowieckip.ecommercewebsite.service;

import hrynowieckip.ecommercewebsite.converter.UserConverter;
import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.domain.repository.UserRepository;
import hrynowieckip.ecommercewebsite.exception.UserAlreadyExistsException;
import hrynowieckip.ecommercewebsite.web.command.RegisterUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long create(RegisterUserCommand registerUserCommand) {
        log.debug("User data to save: {}", registerUserCommand);

        User userToCreate = userConverter.from(registerUserCommand);
        if(userRepository.existsByUsername(userToCreate.getUsername())){
            log.debug("Attempting to register on already existing user");
            throw  new UserAlreadyExistsException(String.format("User %s already exists", userToCreate.getUsername()));
        }
        userToCreate.setActive(Boolean.TRUE);
        userToCreate.setRoles(Set.of("ROLE_USER"));
        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));
        userRepository.save(userToCreate);
        log.debug("Saved user: {}", userToCreate);
        return userToCreate.getId();
    }
}