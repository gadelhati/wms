package com.oms.wms.exception.annotation;

import com.oms.wms.persistence.payload.request.DTORequestOrder;
import com.oms.wms.service.ServiceOrder;
import com.oms.wms.service.ServiceStock;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

import static com.oms.wms.exception.Validator.isNull;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ExistsStock.ValidatorExistsStock.class })
@Documented
public @interface ExistsStock {

    String message() default "Stock {exists}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    class ValidatorExistsStock implements ConstraintValidator<ExistsStock, DTORequestOrder> {

        @Autowired
        private ServiceOrder serviceOrder;
        @Autowired
        private ServiceStock serviceStock;

        @Override
        public boolean isValid(DTORequestOrder value, ConstraintValidatorContext context) {
            return !isNull(value.getStock()) && !isNull(value.getStock().getId())
                    && serviceStock.existsById(value.getStock().getId());
        }
    }
}