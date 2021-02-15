package com.matias.rabbitmq.producer.service

import com.matias.rabbitmq.producer.dto.Order
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class OrderService(private val rabbitTemplate: RabbitTemplate) {

    fun create(order: Order): Order {
        rabbitTemplate.convertAndSend(order)
        return order
    }
}