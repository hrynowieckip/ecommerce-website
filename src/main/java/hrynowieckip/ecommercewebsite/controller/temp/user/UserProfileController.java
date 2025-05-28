package hrynowieckip.ecommercewebsite.controller.temp.user;

import hrynowieckip.ecommercewebsite.domain.dto.UserSummary;
import hrynowieckip.ecommercewebsite.service.APIService;
import hrynowieckip.ecommercewebsite.service.UserService;
import hrynowieckip.ecommercewebsite.domain.dto.EditUserCommand;
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
@RequestMapping("/profile")
@Slf4j
@RequiredArgsConstructor
public class UserProfileController {
    private final UserService userService;
    private final APIService apiService;

    @GetMapping
    public String getProfilePage(Model model) {
        UserSummary userSummary = userService.getCurrentUserSummary();
        EditUserCommand editUserCommand = createEditUserCommand(userSummary);

        model.addAttribute(userSummary);
        model.addAttribute(editUserCommand);
        model.addAttribute("tempForCity", apiService.getWeatherForCity());
        return "user/profile";
    }

    private EditUserCommand createEditUserCommand(UserSummary userSummary) {
        return EditUserCommand.builder()
                .firstName(userSummary.getFirstName())
                .lastName(userSummary.getLastName())
                .address(userSummary.getAddress())
                .zipCode(userSummary.getZipCode())
                .city(userSummary.getCity())
                .phoneNumber(userSummary.getPhoneNumber())
                .build();
    }

    @PostMapping("/edit")
    public String editUserProfile(@Valid EditUserCommand editUserCommand, BindingResult bindingResult) {
        log.debug("User data to edit: {}", editUserCommand);
        try {
            String username = userService.edit(editUserCommand);
            log.debug("User successfully edited, username = {}", username);
        } catch (RuntimeException re) {
            log.debug("Error while editing data", re);
            bindingResult.rejectValue(null, null, "Something went wrong");
            return "/profile/edit";
        }
        return "redirect:/";
    }
}
