package com.logictech.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author JG.Hannibal
 * @since 2017/12/27 22:13
 */
public class MoneyValidator implements ConstraintValidator<Money, String> {

    private String regexp;

    @Override
    public void initialize(Money constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value.matches(regexp)) {
            return true;
        }
        return false;
    }

}
    