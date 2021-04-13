package hrynowieckip.ecommercewebsite.web.controller.product;

import hrynowieckip.ecommercewebsite.data.product.ProductSummary;
import hrynowieckip.ecommercewebsite.exception.ProductNameAlreadyExists;
import hrynowieckip.ecommercewebsite.service.APIService;
import hrynowieckip.ecommercewebsite.service.CategoryService;
import hrynowieckip.ecommercewebsite.service.ProductService;
import hrynowieckip.ecommercewebsite.web.command.AddCommentCommand;
import hrynowieckip.ecommercewebsite.web.command.AddProductCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final APIService apiService;

    @GetMapping("/{name}")
    public String getProductPage(@PathVariable("name") String name, Model model) {
        model.addAttribute("allCategories", categoryService.getAllCategoriesSummary());
        ProductSummary productSummary = productService.getProductSummaryByName(name);
        model.addAttribute("product", productSummary);
        model.addAttribute(new AddCommentCommand());
        return "product/product-page";
    }

    @GetMapping
    public String getProducts(Model model) {
        List<ProductSummary> allProducts = productService.getAllProductsSummary();
        model.addAttribute("allProducts", allProducts);
        model.addAttribute(new AddProductCommand());
        model.addAttribute("allCategories", categoryService.getAllCategoriesSummary());
        model.addAttribute("tempForCity", apiService.getWeatherForCity());
        return "product/form";
    }

    @PostMapping("/add")
    public String addProduct(@Valid AddProductCommand addProductCommand, @RequestParam("image") MultipartFile[] multipartProductImage, BindingResult bindingResult) {
        log.debug("Product data to add: {}", addProductCommand);

        if (bindingResult.hasErrors()) {
            log.debug("Incorrect data : {}", bindingResult.getAllErrors());
            return "product/form";
        }
        try {
            Long id = productService.addProduct(addProductCommand, multipartProductImage);
            log.debug("Product added, id = {}", id);
            return "redirect:/";

        } catch (ProductNameAlreadyExists pnae) {
            bindingResult.rejectValue(null, null, "Product with that name already exists");
            return "product/form";
        } catch (RuntimeException exception) {
            bindingResult.rejectValue(null, null, "Problem with adding product");
            return "product/form";
        }

    }

    @PostMapping("/comment/add")
    public String postComment(AddCommentCommand addCommentCommand) {
        log.debug("Comment to add: {}", addCommentCommand);
        Long id = productService.addComment(addCommentCommand);
        log.debug("Comment saved, id: {}", id);
        return "redirect:/";
    }
}
