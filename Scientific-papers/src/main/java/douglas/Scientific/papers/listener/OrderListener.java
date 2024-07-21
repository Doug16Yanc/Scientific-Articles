package douglas.Scientific.papers.listener;

import douglas.Scientific.papers.dto.OrderCreatedEvent;
import douglas.Scientific.papers.service.OrderService;
import org.slf4j.Logger;
import org.springframework.messaging.Message;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static douglas.Scientific.papers.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderListener {

    private final Logger logger = LoggerFactory.getLogger(OrderListener.class);
    private final OrderService orderService;

    public OrderListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        logger.info("Message : {}", message);
        orderService.saveOrder(message.getPayload());
    }
}

