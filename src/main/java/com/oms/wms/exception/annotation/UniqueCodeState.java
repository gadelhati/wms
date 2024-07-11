package com.oms.wms.exception.annotation;

import com.oms.wms.persistence.payload.request.DTORequestState;
import com.oms.wms.service.ServiceState;
import com.oms.wms.exception.Validator;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueCodeState.ValidatorUniqueCodeState.class })
@Documented
public @interface UniqueCodeState {

    String message() default "{unique}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    class ValidatorUniqueCodeState implements ConstraintValidator<UniqueCodeState, DTORequestState> {

        @Autowired
        private ServiceState serviceState;

        @Override
        public boolean isValid(DTORequestState value, ConstraintValidatorContext context) {
            return !Validator.isNull(value.getCode()) && !serviceState.existsByCode(value.getCode()) ||
                    !Validator.isNull(value.getCode()) && !Validator.isNull(value.getId()) && !serviceState.existsByCodeAndIdNot(value.getCode(), value.getId());
        }
    }
}