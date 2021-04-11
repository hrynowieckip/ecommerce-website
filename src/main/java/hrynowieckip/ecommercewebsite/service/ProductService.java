package hrynowieckip.ecommercewebsite.service;

import hrynowieckip.ecommercewebsite.FileUploadUtil;
import hrynowieckip.ecommercewebsite.converter.CommentConverter;
import hrynowieckip.ecommercewebsite.converter.ProductConverter;
import hrynowieckip.ecommercewebsite.data.product.ProductSummary;
import hrynowieckip.ecommercewebsite.domain.model.Category;
import hrynowieckip.ecommercewebsite.domain.model.Comment;
import hrynowieckip.ecommercewebsite.domain.model.Product;
import hrynowieckip.ecommercewebsite.domain.model.ProductImage;
import hrynowieckip.ecommercewebsite.domain.repository.CategoryRepository;
import hrynowieckip.ecommercewebsite.domain.repository.CommentRepository;
import hrynowieckip.ecommercewebsite.domain.repository.ProductImageRepository;
import hrynowieckip.ecommercewebsite.domain.repository.ProductRepository;
import hrynowieckip.ecommercewebsite.exception.ProductNameAlreadyExists;
import hrynowieckip.ecommercewebsite.web.command.AddCommentCommand;
import hrynowieckip.ecommercewebsite.web.command.AddProductCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
    private final ProductImageRepository productImageRepository;
    private final CommentConverter commentConverter;
    private final CommentRepository commentRepository;

    public List<ProductSummary> getAllProductsSummary() {
        log.debug("Getting all products");
        List<Product> allProducts = productRepository.getAllBy();
        log.debug("Products to convert: {}", allProducts);
        return allProducts.stream()
                .map(productConverter::toProductSummary)
                .collect(Collectors.toList());
    }

    public Long addProduct(AddProductCommand addProductCommand, MultipartFile[] multipartProductImage) {
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

        for (MultipartFile file : multipartProductImage) {
            ProductImage productImage = new ProductImage();
            try {
                 productImage = ProductImage.builder()
                        .image(file.getBytes())
                        .build();
            } catch (IOException e) {
                log.debug("Problem with saving the image");
            }
            productImageRepository.save(productImage);

            List<ProductImage> images = productToAdd.getPhotos();
            if (images == null) images = new ArrayList<>();
            images.add(productImage);
            productToAdd.setPhotos(images);
            productImage.setProduct(productToAdd);
        }

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

    public Long addComment(AddCommentCommand addCommentCommand) {
        Comment comment = commentConverter.from(addCommentCommand);
        Product product = productRepository.getByName(addCommentCommand.getProductName());

        comment.setProduct(product);
        List<Comment> productComments = product.getComments();
        if (productComments == null) product.setComments(new ArrayList<>());
        productComments.add(comment);
        commentRepository.save(comment);
        return comment.getId();
    }
}
