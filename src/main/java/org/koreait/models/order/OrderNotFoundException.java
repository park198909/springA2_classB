package org.koreait.models.order;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends CommonException {

    public OrderNotFoundException() {
        super(bundleValidation.getString("Validation.order.notExists"), HttpStatus.BAD_REQUEST);
    }
}
