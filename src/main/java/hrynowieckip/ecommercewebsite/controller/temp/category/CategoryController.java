package hrynowieckip.ecommercewebsite.controller.temp.category;

import hrynowieckip.ecommercewebsite.domain.dto.CategorySummary;
import hrynowieckip.ecommercewebsite.exception.CategoryNameAlreadyExistsException;
import hrynowieckip.ecommercewebsite.service.APIService;
import hrynowieckip.ecommercewebsite.service.CategoryService;
import hrynowieckip.ecommercewebsite.domain.dto.AddCategoryCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final APIService apiService;

    @GetMapping
    private String getCategories(Model model) {
        List<CategorySummary> categorySummaryList = categoryService.getAllCategoriesSummary();
        model.addAttribute("allCategories", categorySummaryList);
        model.addAttribute(new AddCategoryCommand());
        model.addAttribute("tempForCity", apiService.getWeatherForCity());
        return "category/form";
    }

    @PostMapping("/add")
    private String addCategory(@Valid AddCategoryCommand addCategoryCommand, BindingResult bindingResult) {
        log.debug("Category data to add: {}", addCategoryCommand);
        if (bindingResult.hasErrors()) {
            log.debug("Incorrect data: {}", bindingResult.getAllErrors());
            return "category/form";
        }
        try {
            Long id = categoryService.addCategory(addCategoryCommand);
            log.debug("Category added, id = {}", id);
            return "redirect:/category";
        } catch (CategoryNameAlreadyExistsException cnaee) {
            bindingResult.rejectValue("name", null, "This category name already exists");
            return "category/form";
        } catch (RuntimeException exception) {
            bindingResult.rejectValue(null, null, "Problem with adding category");
            return "category/form";
        }
    }

    @GetMapping("/{name}")
    public String displayProductsFromCategory(@PathVariable("name") String categoryName, Model model) {
        model.addAttribute("categoryName", categoryName);
        List<CategorySummary> categorySummaryList = categoryService.getAllCategoriesSummary();
        model.addAttribute("allCategories", categorySummaryList);
        Set productsFromCategory = categoryService.getAllProductsFromCategory(categoryName);
        model.addAttribute("allProducts", productsFromCategory);

        model.addAttribute("tempForCity", apiService.getWeatherForCity());
        return "category/products-from-category";
    }
}
