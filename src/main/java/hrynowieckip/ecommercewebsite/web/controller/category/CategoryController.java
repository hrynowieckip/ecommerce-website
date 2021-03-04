package hrynowieckip.ecommercewebsite.web.controller.category;

import hrynowieckip.ecommercewebsite.data.category.CategorySummary;
import hrynowieckip.ecommercewebsite.exception.CategoryNameAlreadyExistsException;
import hrynowieckip.ecommercewebsite.service.CategoryService;
import hrynowieckip.ecommercewebsite.web.command.AddCategoryCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    private String getCategories(Model model) {
        List<CategorySummary> categorySummaryList = categoryService.getAllCategoriesSummary();
        model.addAttribute("allCategories", categorySummaryList);
        model.addAttribute(new AddCategoryCommand());
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
}
