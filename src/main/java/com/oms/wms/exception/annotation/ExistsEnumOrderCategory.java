package com.oms.wms.exception.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ExistsEnumOrderCategory.ValidatorExistsEnumOrderCategory.class })
@Documented
public @interface ExistsEnumOrderCategory {

    Class<? extends Enum<?>> enumClass();
    String message() default "{enum}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    class ValidatorExistsEnumOrderCategory implements ConstraintValidator<ExistsEnumOrderCategory, CharSequence> {
        private List<String> values;

        @Override
        public void initialize(ExistsEnumOrderCategory enumValue) {
            values = Stream.of(enumValue.enumClass().getEnumConstants())
                    .map(Enum::name)
                    .collect(Collectors.toList());
        }
        @Override
        public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }
            return values.contains(value.toString());
        }
    }
}