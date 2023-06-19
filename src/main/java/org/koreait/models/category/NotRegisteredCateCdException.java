package org.koreait.models.category;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

public class NotRegisteredCateCdException extends CommonException {
    public NotRegisteredCateCdException() {
        super(bundleValidation.getString("NotRegistered.cateCd"), HttpStatus.BAD_REQUEST);
    }
}
