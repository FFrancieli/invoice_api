package com.acme.validators;

import com.acme.models.InvoicePayload;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class InvoicePayloadValidator implements Validator {

    private static final String EMPTY_FIELD_CODE = "requiredField";
    private static final String EMPTY_FIELD_MESSAGE = "Field is required";

    @Override
    public boolean supports(Class<?> clazz) {
        return InvoicePayload.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paymentDueDate", EMPTY_FIELD_CODE, EMPTY_FIELD_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressId", EMPTY_FIELD_CODE, EMPTY_FIELD_MESSAGE);
        ValidationUtils.rejectIfEmpty(errors, "total", EMPTY_FIELD_CODE, EMPTY_FIELD_MESSAGE);
        ValidationUtils.rejectIfEmpty(errors, "amount", EMPTY_FIELD_CODE, EMPTY_FIELD_MESSAGE);
        ValidationUtils.rejectIfEmpty(errors, "customerId", EMPTY_FIELD_CODE, EMPTY_FIELD_MESSAGE);
    }
}
