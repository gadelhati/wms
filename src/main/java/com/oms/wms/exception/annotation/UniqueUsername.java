package com.oms.wms.exception.annotation;

import com.oms.wms.persistence.payload.request.DTORequestUser;
import com.oms.wms.service.ServiceUser;
import com.oms.wms.exception.Validator;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueUsername.ValidatorUniqueUsername.class })
@Documented
public @interface UniqueUsername {

    String message() default "{unique}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    class ValidatorUniqueUsername implements ConstraintValidator<UniqueUsername, DTORequestUser> {

        @Autowired
        private ServiceUser serviceUser;

        @Override
        public boolean isValid(DTORequestUser value, ConstraintValidatorContext context) {
            return !Validator.isNull(value.getUsername()) && !serviceUser.existsByName(value.getUsername()) ||
                    !Validator.isNull(value.getUsername()) && !Validator.isNull(value.getId()) && !serviceUser.existsByNameAndIdNot(value.getUsername(), value.getId());
        }
    }
}