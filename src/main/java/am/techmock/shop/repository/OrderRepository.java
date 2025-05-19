package am.techmock.shop.repository;

import am.techmock.shop.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class OrderRepository {
	private final List<Order> orders = new ArrayList<>();
	private final AtomicInteger idGenerator = new AtomicInteger(1);

	public List<Order> list() {
		return new ArrayList<>(orders);
	}

	public void add(Order order) {
		Order orderWithId = new Order(
				idGenerator.getAndIncrement(),
				order.items(),
				order.contact(),
				order.price(),
				false
		);
		orders.add(orderWithId);

	}

	public void remove(int id) {
		orders.removeIf(order -> order.id() == id);
	}
}
