package com.example.pavel_shumilin_shop.domain.model

/**
 * Модель для создания заказа
 */
data class CreateOrderModel(
    var name: String = "",
    var surname: String = "",
    var middleName: String = "",
    var phoneNumber : String = ""
)