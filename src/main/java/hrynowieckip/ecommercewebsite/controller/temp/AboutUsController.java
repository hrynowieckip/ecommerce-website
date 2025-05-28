package hrynowieckip.ecommercewebsite.controller.temp;

import hrynowieckip.ecommercewebsite.service.APIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aboutus")
@RequiredArgsConstructor
public class AboutUsController {
    private final APIService apiService;

    @GetMapping
    public String displayAboutUs(Model model) {
        model.addAttribute("tempForCity", apiService.getWeatherForCity());
        return "about-us/about-us";
    }
}
