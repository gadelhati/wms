package com.oms.wms.exception.annotation;

import com.oms.wms.persistence.model.OrderItem;
import com.oms.wms.persistence.payload.request.DTORequestOrder;
import com.oms.wms.service.ServiceOrder;
import com.oms.wms.service.ServiceOrderItem;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

import static com.oms.wms.exception.Validator.isNull;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ExistsOrderItem.ValidatorExistsOrderItem.class })
@Documented
public @interface ExistsOrderItem {

    String message() default "Order Item {exists}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    class ValidatorExistsOrderItem implements ConstraintValidator<ExistsOrderItem, DTORequestOrder> {

        @Autowired
        private ServiceOrder serviceOrder;
        @Autowired
        private ServiceOrderItem serviceOrderItem;

        @Override
        public boolean isValid(DTORequestOrder value, ConstraintValidatorContext context) {
            boolean valid = true;
            if (!isNull(value.getOrderItem())) {
                for (OrderItem orderItem : value.getOrderItem()) {
                    if (isNull(orderItem.getId()) || orderItem.getId().equals("") || !serviceOrderItem.existsById(orderItem.getId())) {
                        valid = false;
                        break;
                    }
                }
            }
            return !isNull(value.getOrderItem()) && valid;
        }
    }
}