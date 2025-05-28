package hrynowieckip.ecommercewebsite.converter;

import hrynowieckip.ecommercewebsite.domain.dto.CategorySummary;
import hrynowieckip.ecommercewebsite.domain.model.Category;
import hrynowieckip.ecommercewebsite.domain.dto.AddCategoryCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryConverterTest {

    private CategoryConverter categoryConverter;

    @BeforeEach
    public void setup() {
        categoryConverter = new CategoryConverter();
    }

    @Test
    public void toCategorySummaryTest() {
        //Given
        CategorySummary expected = CategorySummary.builder()
                .name("Category")
                .build();

        Category category = Category.builder()
                .name("Category")
                .build();
        //When
        CategorySummary result = categoryConverter.toCategorySummary(category);
        //Then
        assertEquals(expected, result);
    }

    @Test
    public void fromTest() {
        //Given
        Category expected = Category.builder()
                .name("Category")
                .build();

        AddCategoryCommand category = AddCategoryCommand.builder()
                .name("Category")
                .build();
        //When
        Category result = categoryConverter.from(category);
        //Then
        assertEquals(expected, result);
    }

}