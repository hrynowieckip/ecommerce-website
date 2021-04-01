package hrynowieckip.ecommercewebsite.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aboutus")
public class AboutUsController {
    @GetMapping
    public String displayAboutUs(){
        return "about-us/about-us";
    }
}
