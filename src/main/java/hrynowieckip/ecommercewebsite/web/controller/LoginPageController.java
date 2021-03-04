package hrynowieckip.ecommercewebsite.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginPageController {
    @PostMapping
    public String postLogin() {
        return "home-page";
    }

    @GetMapping
    public String getLogin() {
        return "login/form";
    }
}
