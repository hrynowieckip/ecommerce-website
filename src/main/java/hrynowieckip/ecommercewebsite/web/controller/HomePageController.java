package hrynowieckip.ecommercewebsite.web.controller;


import hrynowieckip.ecommercewebsite.domain.repository.ProductRepository;
import hrynowieckip.ecommercewebsite.service.CategoryService;
import hrynowieckip.ecommercewebsite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomePageController {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    @GetMapping
    public String getTest(Model model){
        model.addAttribute("allCategories", categoryService.getAllCategoriesSummary());
        model.addAttribute("allProducts", productService.getAllProductsSummary());
        return "home-page";
    }
}
