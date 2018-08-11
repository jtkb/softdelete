package com.example.validation.annotation;

import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueConstraint
{
    String message() default "Properties are not unique";

    String propertyName() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean isSoftDeleteFlag() default false;
}
