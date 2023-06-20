package org.koreait.models.file;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

public class FileTypeException extends CommonException {
    public FileTypeException(String code) {
        super(bundleValidation.getString(code), HttpStatus.BAD_REQUEST);
    }
}
