package com.oms.wms.exception.annotation;

import com.oms.wms.persistence.payload.request.DTORequestRole;
import com.oms.wms.service.ServiceRole;
import com.oms.wms.exception.Validator;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueNameRole.ValidatorUniqueNameRole.class })
@Documented
public @interface UniqueNameRole {

    String message() default "{unique}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    class ValidatorUniqueNameRole implements ConstraintValidator<UniqueNameRole, DTORequestRole> {

        @Autowired
        private ServiceRole serviceRole;

        @Override
        public boolean isValid(DTORequestRole value, ConstraintValidatorContext context) {
            return !Validator.isNull(value.getName()) && !serviceRole.existsByName(value.getName()) ||
                    !Validator.isNull(value.getName()) && !Validator.isNull(value.getId()) && !serviceRole.existsByNameAndIdNot(value.getName(), value.getId());
        }
    }
}