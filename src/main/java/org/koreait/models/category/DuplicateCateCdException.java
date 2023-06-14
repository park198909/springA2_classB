package org.koreait.models.category;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

public class DuplicateCateCdException extends CommonException {
    private static String message;
    static {
        ResourceBundle rs = ResourceBundle.getBundle("messages.validation");
        message = rs.getString("Duplicate.categoryForm.cateCd");
    }
    public DuplicateCateCdException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
