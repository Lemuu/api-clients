package info.lemuu.apiclients.validation;

import info.lemuu.apiclients.validation.contraints.RG;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Lemuel Brenner
 */
public class RGValidation implements ConstraintValidator<RG, String> {

    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext constraintValidatorContext
    ) {
        return value != null && value.matches("[0-9]{2,3}\\.?[0-9]{2,3}\\.?[0-9]{3}-?[0-9]?");
    }

}