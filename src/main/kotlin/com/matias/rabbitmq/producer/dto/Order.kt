package com.matias.rabbitmq.producer.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.util.*

data class Order(
    val id: String = UUID.randomUUID().toString(),
    val customer: String,
    val price: BigDecimal,
    val status: OrderStatus = OrderStatus.PENDING
)

enum class OrderStatus {
    PENDING, DONE, CANCELLED
}