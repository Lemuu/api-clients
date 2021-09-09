package info.lemuu.apiclients.validation;

import info.lemuu.apiclients.validation.contraints.CEP;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Lemuel Brenner
 */
public class CEPValidation implements ConstraintValidator<CEP, String> {

    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext constraintValidatorContext
    ) {
        return value != null && value.matches("[0-9]{5}-[0-9]{3}");
    }

}