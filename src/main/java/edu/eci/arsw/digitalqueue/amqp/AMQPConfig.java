package edu.eci.arsw.digitalqueue.amqp;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    @Bean
    public DirectExchange turnsDirect() {
        return new DirectExchange("turns.direct");
    }

}
