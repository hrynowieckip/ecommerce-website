package hrynowieckip.ecommercewebsite.converter;

import hrynowieckip.ecommercewebsite.domain.dto.CategorySummary;
import hrynowieckip.ecommercewebsite.domain.model.Category;
import hrynowieckip.ecommercewebsite.domain.dto.AddCategoryCommand;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategorySummary toCategorySummary(Category category) {
        return CategorySummary.builder()
                .name(category.getName())
                .build();
    }

    public Category from(AddCategoryCommand addCategoryCommand) {
        return Category.builder()
                .name(addCategoryCommand.getName())
                .build();
    }
}
