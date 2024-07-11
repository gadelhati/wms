package com.oms.wms.exception.annotation;

import com.oms.wms.persistence.payload.request.DTORequestCity;
import com.oms.wms.service.ServiceCity;
import com.oms.wms.exception.Validator;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueCodeCity.ValidatorUniqueCodeCity.class })
@Documented
public @interface UniqueCodeCity {

    String message() default "{unique}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    class ValidatorUniqueCodeCity implements ConstraintValidator<UniqueCodeCity, DTORequestCity> {

        @Autowired
        private ServiceCity serviceCity;

        @Override
        public boolean isValid(DTORequestCity value, ConstraintValidatorContext context) {
            return !Validator.isNull(value.getCode()) && !serviceCity.existsByCode(value.getCode()) ||
                    !Validator.isNull(value.getCode()) && !Validator.isNull(value.getId()) && !serviceCity.existsByCodeAndIdNot(value.getCode(), value.getId());
        }
    }
}