package hrynowieckip.ecommercewebsite.service;

import hrynowieckip.ecommercewebsite.converter.CategoryConverter;
import hrynowieckip.ecommercewebsite.data.category.CategorySummary;
import hrynowieckip.ecommercewebsite.domain.model.Category;
import hrynowieckip.ecommercewebsite.domain.repository.CategoryRepository;
import hrynowieckip.ecommercewebsite.exception.CategoryNameAlreadyExistsException;
import hrynowieckip.ecommercewebsite.web.command.AddCategoryCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public List<CategorySummary> getAllCategoriesSummary() {
        log.debug("Getting all categories");
        List<Category> allCategories = categoryRepository.getAll();
        log.debug("Categories to convert: {}", allCategories);
        return allCategories.stream()
                .map(categoryConverter::toCategorySummary)
                .collect(Collectors.toList());
    }

    public Long addCategory(AddCategoryCommand addCategoryCommand) {
        log.debug("Category data to save {}", addCategoryCommand);

        Category categoryToSave = categoryConverter.from(addCategoryCommand);
        if(categoryRepository.existsByName(categoryToSave.getName())){
            log.debug("Category name already exists = {}", categoryToSave.getName());
            throw new CategoryNameAlreadyExistsException(String.format("Category name %s already exists", categoryToSave.getName()));
        }
        categoryRepository.save(categoryToSave);
        return categoryToSave.getId();
    }
}
