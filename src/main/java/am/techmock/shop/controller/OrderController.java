package am.techmock.shop.controller;

import am.techmock.shop.model.Order;
import am.techmock.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderRepository orderRepository;

	@Autowired
	public OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@GetMapping
	public List<Order> list() {
		return orderRepository.list();
	}

	@PostMapping()
	public void add(@RequestBody Order order) {
		orderRepository.add(order);
	}

	@PostMapping("/{id}")
	public void remove(@PathVariable int id) {
		orderRepository.remove(id);
	}
}
