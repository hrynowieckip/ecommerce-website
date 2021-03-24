package hrynowieckip.ecommercewebsite.web.controller;

import hrynowieckip.ecommercewebsite.data.product.ProductSummary;
import hrynowieckip.ecommercewebsite.service.UserService;
import hrynowieckip.ecommercewebsite.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    private final UserService userService;

    @GetMapping
    public String displayUserWishlist(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Set<ProductSummary> allProductForUserFromWishlist = wishlistService.getAllProductForUserFromWishlist(username);
        model.addAttribute("allProducts", allProductForUserFromWishlist);
        return "wishlist/products-from-wishlist";
    }

    @GetMapping("/{name}")
    public String addProductToWishlist(@PathVariable("name") String productName) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long id = wishlistService.addProductToWishlist(productName, username);
        log.debug("Product added to wishlist, id: {}", id);
        return "redirect:/";
    }
    @GetMapping("/delete/{name}")
    public String deleteProductFromWishlist(@PathVariable("name") String productName) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String productNameDeleted = wishlistService.deleteProductFromWishlist(productName, username);
        log.debug("Product deleted from wishlist: {}", productNameDeleted);
        return "redirect:/";
    }
}
