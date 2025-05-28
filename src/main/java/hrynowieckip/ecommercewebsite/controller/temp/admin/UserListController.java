package hrynowieckip.ecommercewebsite.controller.temp.admin;


import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.service.APIService;
import hrynowieckip.ecommercewebsite.service.AdminService;
import hrynowieckip.ecommercewebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/userlist")
public class UserListController {
    private final UserService userService;
    private final AdminService adminService;
    private final APIService apiService;

    @GetMapping
    public String displayAllUsers(Model model) {
        log.debug("Getting all users");
        List<User> users = userService.getAllUsers();
        model.addAttribute("allUsers", users);
        model.addAttribute("tempForCity", apiService.getWeatherForCity());
        return "admin/user-list";
    }

    @PostMapping("/edit")
    public String updateUserRoles(@RequestParam String username, @RequestParam String role) {
        log.debug("Changing roles for user: {}", username);
        if (role.equals("admin")) {
            Long id = adminService.addAdminRoleToUser(username);
            log.debug("Role \"admin\" successfully added to user, id: {}", id);
            return "redirect:/";
        } else if (role.equals("user")) {
            Long id = adminService.deleteOrMakeSureThatUserDontHaveAdminRole(username);
            log.debug("Role \"admin\" removed added to user, id: {}", id);
            return "redirect:/";
        }
        log.debug("Some error occured");
        return "redirect:/";
    }
}
