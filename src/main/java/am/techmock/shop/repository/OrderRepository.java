package am.techmock.shop.repository;

import am.techmock.shop.model.order.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class OrderRepository {

	private final Map<Integer, Order> orders = new ConcurrentHashMap<>();
	private final AtomicInteger idGenerator = new AtomicInteger(1);

	public List<Order> list() {
		return new ArrayList<>(orders.values());
	}

	public void add(Order order) {
		int id = idGenerator.getAndIncrement();
		Order orderWithId = new Order(
				id,
				order.items(),
				order.contact(),
				order.price(),
				false
		);
		orders.put(id, orderWithId);
	}

	public void markAsDone(int id) {
		var order = orders.get(id);
		if (order != null) {
			orders.put(id, new Order(
					order.id(),
					order.items(),
					order.contact(),
					order.price(),
					true
			));
		}
	}

	public void remove(int id) {
		orders.remove(id);
	}
}
