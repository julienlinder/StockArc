package ch.hearc.stockarc.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ch.hearc.stockarc.model.Rent;

@Component
public class RentValidator implements Validator {

    Logger logger = LoggerFactory.getLogger(RentValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return Rent.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tool", "NotEmpty");
    }

}