package hrynowieckip.ecommercewebsite.controller.temp;

import hrynowieckip.ecommercewebsite.domain.dto.ProductSummary;
import hrynowieckip.ecommercewebsite.domain.dto.UserSummary;
import hrynowieckip.ecommercewebsite.service.APIService;
import hrynowieckip.ecommercewebsite.service.CartService;
import hrynowieckip.ecommercewebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final APIService apiService;


    @GetMapping
    public String displayUserCart(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ProductSummary> allProductForUserFromCart = cartService.getAllProductForUserFromCart(username);
        model.addAttribute("allProducts", allProductForUserFromCart);
        UserSummary userSummary = userService.getCurrentUserSummary();
        model.addAttribute("currentUser", userSummary);
        model.addAttribute("tempForCity", apiService.getWeatherForCity());
        return "cart/cart-page";
    }

    @GetMapping("/{name}")
    public String addProductToUserCart(@PathVariable("name") String productName) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long id = cartService.addProductToCart(productName, username);
        log.debug("Product added to user cart, id: {}", id);
        return "redirect:/";
    }

    @GetMapping("/delete/{name}")
    public String deleteProductFromCart(@PathVariable("name") String productName) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String productNameDeleted = cartService.deleteProductFromUserCart(productName, username);
        log.debug("Product deleted from cart: {}", productNameDeleted);
        return "redirect:/";
    }
}
