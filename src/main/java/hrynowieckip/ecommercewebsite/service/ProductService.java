package hrynowieckip.ecommercewebsite.service;

import hrynowieckip.ecommercewebsite.converter.ProductConverter;
import hrynowieckip.ecommercewebsite.data.product.ProductSummary;
import hrynowieckip.ecommercewebsite.domain.model.Category;
import hrynowieckip.ecommercewebsite.domain.model.Product;
import hrynowieckip.ecommercewebsite.domain.repository.CategoryRepository;
import hrynowieckip.ecommercewebsite.domain.repository.ProductRepository;
import hrynowieckip.ecommercewebsite.exception.ProductNameAlreadyExists;
import hrynowieckip.ecommercewebsite.web.command.AddProductCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final CategoryRepository categoryRepository;

    public List<ProductSummary> getAllProductsSummary() {
        log.debug("Getting all products");
        List<Product> allProducts = productRepository.getAllBy();
        log.debug("Products to convert: {}", allProducts);
        return allProducts.stream()
                .map(productConverter::toProductSummary)
                .collect(Collectors.toList());
    }

    public Long addProduct(AddProductCommand addProductCommand) {
        log.debug("Product data to save : {}", addProductCommand);

        Product productToAdd = productConverter.from(addProductCommand);
        if (productRepository.existsByName(productToAdd.getName())) {
            log.debug("Product name already exists");
            throw new ProductNameAlreadyExists(String.format("Product with name %s already exists", productToAdd.getName()));
        }
        Category category = categoryRepository.getByName(addProductCommand.getCategory());
        productToAdd.setCategory(category);
        Set<Product> productsListFromCategory = category.getProducts();
        if (productsListFromCategory == null) productsListFromCategory = new HashSet<>();
        productsListFromCategory.add(productToAdd);
        category.setProducts(productsListFromCategory);

        productRepository.save(productToAdd);
        log.debug("Product saved: {}", productToAdd);
        return productToAdd.getId();

    }


    public ProductSummary getProductSummaryByName(String name) {
        log.debug("Getting product by name: {}", name);
        Product product = productRepository.getByName(name);
        log.debug("Convert product to summary");
        ProductSummary productSummary = productConverter.toProductSummary(product);
        return productSummary;
    }
}
