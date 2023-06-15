package org.koreait.commons.validators;

import org.springframework.validation.Errors;

public interface ServiceValidator<T> {
    void check(T t);

    default void check(T t, Errors errors) {
        if (errors != null && errors.hasErrors()){
            return;
        }

        this.check(t);
    }
}
