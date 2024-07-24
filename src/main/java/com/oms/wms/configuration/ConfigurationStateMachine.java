package com.oms.wms.configuration;

import com.oms.wms.persistence.OrderEvent;
import com.oms.wms.persistence.OrderState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class ConfigurationStateMachine extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> config) throws Exception {
        config.withStates()
                .initial(OrderState.NEW)
                .states(EnumSet.allOf(OrderState.class))
                .end(OrderState.COMPLETED)
                .end(OrderState.CANCELLED);
    }
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> config) throws Exception {
        config
                .withExternal().source(OrderState.NEW).target(OrderState.VALIDATED).event(OrderEvent.VALIDATE)
                .action(null).and()
                .withExternal().source(OrderState.VALIDATED).target(OrderState.PAID).event(OrderEvent.PAY)
                .action(payOrderAction()).and()
                .withExternal().source(OrderState.PAID).target(OrderState.SHIPPED).event(OrderEvent.SHIP)
                .action(null).and()
                .withExternal().source(OrderState.SHIPPED).target(OrderState.COMPLETED).event(OrderEvent.COMPLETE).and()
                .withExternal().source(OrderState.VALIDATED).target(OrderState.CANCELLED).event(OrderEvent.CANCEL).and()
                .withExternal().source(OrderState.PAID).target(OrderState.CANCELLED).event(OrderEvent.CANCEL);
    }
    @Bean
    Action<OrderState, OrderEvent> payOrderAction() {
        return context -> {
            System.out.println("Chamada ao serviço ou api externa");
        };
    }
}