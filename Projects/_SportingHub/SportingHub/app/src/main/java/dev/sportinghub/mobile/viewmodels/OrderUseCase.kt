package dev.sportinghub.mobile.viewmodels

import dev.sportinghub.mobile.model.orders.Order
import dev.sportinghub.mobile.model.orders.OrderRow
import dev.sportinghub.mobile.model.posts.Variant
import dev.sportinghub.mobile.repository.orders.OrderRepository
import dev.sportinghub.mobile.repository.orders.OrderRowRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class OrderUseCase @Inject constructor(
    val orderRepository: OrderRepository,
    val orderRowRepository: OrderRowRepository
) {
    suspend fun insert(order: Order): Int {
        return orderRepository.insert(order).toInt()
    }

    suspend fun getOrderById(id: Int): Order? {
        return orderRepository.getByUniqueFields(Order(id = id)).firstOrNull()
    }

    suspend fun getAllOrders(): MutableList<Order> {
        val searchResult = mutableListOf<Order>()
        orderRepository.getAllByFields().collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun search(filterModel: Order): MutableList<Order> {
        val searchResult = mutableListOf<Order>()
        orderRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun insert(orderRow: OrderRow): Int {
        return orderRowRepository.insert(orderRow).toInt()
    }

    suspend fun getOrderRowById(id: Int): OrderRow? {
        return orderRowRepository.getByUniqueFields(OrderRow(id = id)).firstOrNull()
    }

    suspend fun getAllOrderRows(): MutableList<OrderRow> {
        val searchResult = mutableListOf<OrderRow>()
        orderRowRepository.getAllByFields().collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun search(filterModel: OrderRow): MutableList<OrderRow> {
        val searchResult = mutableListOf<OrderRow>()
        orderRowRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        return searchResult
    }
}
