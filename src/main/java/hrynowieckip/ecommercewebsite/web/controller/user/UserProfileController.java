package hrynowieckip.ecommercewebsite.web.controller.user;

import hrynowieckip.ecommercewebsite.data.user.UserSummary;
import hrynowieckip.ecommercewebsite.service.UserService;
import hrynowieckip.ecommercewebsite.web.command.EditUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
@Slf4j @RequiredArgsConstructor
public class UserProfileController {
    private final UserService userService;

    @GetMapping
    public String getProfilePage(Model model){
        UserSummary userSummary= userService.getCurrentUserSummary();
        EditUserCommand editUserCommand = createEditUserCommand(userSummary);

        model.addAttribute(userSummary);
        model.addAttribute(editUserCommand);
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
    public String editUserProfile(@Valid EditUserCommand editUserCommand, BindingResult bindingResult){
        log.debug("User data to edit: {}", editUserCommand);
        try{
            String username = userService.edit(editUserCommand);
            log.debug("User successfully edited, username = {}", username);
        }catch (RuntimeException re){
            log.debug("Error while editing data", re);
            bindingResult.rejectValue(null, null, "Something went wrong");
            return "/profile/edit";
        }
        return "redirect:/";
    }
}
