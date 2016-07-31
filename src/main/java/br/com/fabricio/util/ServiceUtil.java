package br.com.fabricio.util;

import java.util.Set;

import javax.validation.ConstraintViolation;

public final class ServiceUtil<T>{

    private ServiceUtil() {
        throw new AssertionError();
    }

    public static <T> String getErrors(Set<ConstraintViolation<T>> validationErrors) {
        String str = "";
        if (!validationErrors.isEmpty()) {
            for (ConstraintViolation<T> constraintViolation : validationErrors) {
                if (!str.isEmpty()){
                    str += ", ";
                }
                str += constraintViolation.getMessage();
            }
        }
        return str;
    }
}