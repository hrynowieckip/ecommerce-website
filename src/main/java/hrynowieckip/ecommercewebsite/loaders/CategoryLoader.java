package hrynowieckip.ecommercewebsite.loaders;

import hrynowieckip.ecommercewebsite.domain.model.Category;
import hrynowieckip.ecommercewebsite.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CategoryLoader implements DataLoader {
    private final CategoryRepository categoryRepository;

    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Transactional
    public void loadData() {
//        Category category = Category.builder()
//                .name("Other")
//                .build();
//
//        categoryRepository.save(category);
//        log.debug("Saved category: {}", category);

    }
}
