package hrynowieckip.ecommercewebsite.web.controller;

import hrynowieckip.ecommercewebsite.converter.UserConverter;
import hrynowieckip.ecommercewebsite.data.product.ProductSummary;
import hrynowieckip.ecommercewebsite.data.user.UserSummary;
import hrynowieckip.ecommercewebsite.service.CartService;
import hrynowieckip.ecommercewebsite.service.UserService;
import hrynowieckip.ecommercewebsite.web.command.EditUserCommand;
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


    @GetMapping
    public String displayUserCart(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ProductSummary> allProductForUserFromCart = cartService.getAllProductForUserFromCart(username);
        model.addAttribute("allProducts", allProductForUserFromCart);
        UserSummary userSummary= userService.getCurrentUserSummary();
        model.addAttribute("currentUser", userSummary);
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
