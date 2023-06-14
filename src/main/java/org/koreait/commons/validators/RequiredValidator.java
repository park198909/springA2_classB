package org.koreait.commons.validators;

import org.koreait.commons.CommonException;

/**
 * 필수 데이터 검증
 *
 */
public interface RequiredValidator {
    default void requiredCheck(String str, RuntimeException e) {
        if (str == null || str.isBlank()) {
            throw e;
        }
    }

    default void nullCheck(Object o, CommonException e) {
        if (o == null) {
            throw e;
        }
    }
}
