package info.lemuu.apiclients.validation.contraints;

import info.lemuu.apiclients.validation.CEPValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Lemuel Brenner
 */
@Documented
@Constraint(validatedBy = CEPValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface CEP {

    String message() default "CEP invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}