package ch.hearc.stockarc.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ch.hearc.stockarc.model.User;
import ch.hearc.stockarc.service.UserService;

@Component
public class UserValidator implements Validator {

    Logger logger = LoggerFactory.getLogger(UserValidator.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (user.getName().length() < 6 || user.getName().length() > 32) {
            logger.info("test1");
            errors.rejectValue("name", "Size.userForm.username");
        }
        if (userService.findByName(user.getName()) != null) {
            logger.info("test2");
            errors.rejectValue("name", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            logger.info("test3");
            errors.rejectValue("password", "Size.userForm.password");
        }
    }

}