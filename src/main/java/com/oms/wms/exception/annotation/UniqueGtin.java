package com.oms.wms.exception.annotation;

import com.oms.wms.persistence.payload.request.DTORequestItem;
import com.oms.wms.service.ServiceItem;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

import static com.oms.wms.exception.Validator.isNull;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueGtin.ValidatorUniqueGtin.class })
@Documented
public @interface UniqueGtin {

    String message() default "{unique}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    class ValidatorUniqueGtin implements ConstraintValidator<UniqueGtin, DTORequestItem> {

        @Autowired
        private ServiceItem serviceItem;

        @Override
        public boolean isValid(DTORequestItem value, ConstraintValidatorContext context) {
            return !isNull(value.getGtin()) && !serviceItem.existsByGtin(value.getGtin()) ||
                    !isNull(value.getGtin()) && !isNull(value.getId()) && !serviceItem.existsByGtinAndIdNot(value.getGtin(), value.getId());
        }
    }
}