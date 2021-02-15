package com.matias.rabbitmq.producer.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Exchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

private const val QUEUE = "example-queue"
private const val EXCHANGE = "example-exchange"
private const val ROUTING_KEY = "example-routing-key"

@Configuration
class ProducerConfig {

    @Bean
    fun queue() = Queue(QUEUE)

    @Bean
    fun exchange() = TopicExchange(EXCHANGE)

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange) =
        BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY)

    @Bean
    fun messageConverter() = Jackson2JsonMessageConverter(ObjectMapper().registerModule(KotlinModule()))

    @Bean
    fun template(messageConverter: Jackson2JsonMessageConverter, connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.setExchange(EXCHANGE)
        rabbitTemplate.routingKey = ROUTING_KEY
        rabbitTemplate.messageConverter = messageConverter
        return rabbitTemplate
    }
}