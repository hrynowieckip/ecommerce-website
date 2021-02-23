package hrynowieckip.ecommercewebsite.exception;

public class ProductNameAlreadyExists extends RuntimeException {
    public ProductNameAlreadyExists(String message) {
        super(message);
    }
}
