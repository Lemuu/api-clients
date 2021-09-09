package info.lemuu.apiclients.validation.contraints;

import info.lemuu.apiclients.validation.RGValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Lemuel Brenner
 */
@Documented
@Constraint(validatedBy = RGValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface RG {

    String message() default "RG invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}