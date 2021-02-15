package com.matias.rabbitmq.producer.controller

import com.matias.rabbitmq.producer.dto.Order
import com.matias.rabbitmq.producer.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody order: Order): Order = orderService.create(order)
}