package hrynowieckip.ecommercewebsite.controller.temp;

import hrynowieckip.ecommercewebsite.exception.UserAlreadyExistsException;
import hrynowieckip.ecommercewebsite.service.UserService;
import hrynowieckip.ecommercewebsite.domain.dto.RegisterUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String getRegister(Model model) {
        model.addAttribute(new RegisterUserCommand());
        return "register/form";
    }

    @PostMapping
    public String postRegister(@Valid RegisterUserCommand registerUserCommand, BindingResult bindingResult) {
        log.debug("Data to create a user: {}", registerUserCommand);
        if (bindingResult.hasErrors()) {
            log.debug("Incorrect data: {}", bindingResult.getAllErrors());
            return "register/form";
        }

        try {
            Long id = userService.create(registerUserCommand);
            log.debug("User created, id = {}", id);
            return "redirect:/login";
        } catch (UserAlreadyExistsException exception) {
            bindingResult.rejectValue("username", null, "This username is already taken");
            return "register/form";
        } catch (RuntimeException exception) {
            bindingResult.rejectValue(null, null, "Registration problem");
            return "register/form";
        }
    }


//    @ExceptionHandler(UserAlreadyExistsException.class)
//    public String proccesException(Model model){
//        return "";
//    }
}
