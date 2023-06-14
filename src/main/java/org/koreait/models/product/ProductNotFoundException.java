package org.koreait.models.product;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends CommonException {
    public ProductNotFoundException() {
        super(bundleValidation.getString("Validation.product.notExists"), HttpStatus.BAD_REQUEST);
    }
}
