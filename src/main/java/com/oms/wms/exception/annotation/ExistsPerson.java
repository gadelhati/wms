package com.oms.wms.exception.annotation;

import com.oms.wms.persistence.payload.request.DTORequestOrder;
import com.oms.wms.service.ServiceOrder;
import com.oms.wms.service.ServicePerson;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

import static com.oms.wms.exception.Validator.isNull;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ExistsPerson.ValidatorExistsPerson.class })
@Documented
public @interface ExistsPerson {

    String message() default "Person {exists}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    class ValidatorExistsPerson implements ConstraintValidator<ExistsPerson, DTORequestOrder> {

        @Autowired
        private ServiceOrder serviceOrder;
        @Autowired
        private ServicePerson servicePerson;

        @Override
        public boolean isValid(DTORequestOrder value, ConstraintValidatorContext context) {
            return !isNull(value.getPerson()) && !isNull(value.getPerson().getId())
                    && servicePerson.existsById(value.getPerson().getId());
        }
    }
}