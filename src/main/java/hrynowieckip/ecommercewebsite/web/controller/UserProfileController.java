package hrynowieckip.ecommercewebsite.web.controller;

import hrynowieckip.ecommercewebsite.data.user.UserSummary;
import hrynowieckip.ecommercewebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
@Slf4j @RequiredArgsConstructor
public class UserProfileController {
    private final UserService userService;

    @GetMapping
    public String getProfilePage(Model model){
        UserSummary userSummary= userService.getCurrentUserSummary();
        model.addAttribute("userSummary", userSummary);
        return "user/profile";
    }
}
