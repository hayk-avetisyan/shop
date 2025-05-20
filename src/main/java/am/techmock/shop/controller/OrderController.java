package am.techmock.shop.controller;

import am.techmock.shop.model.order.Order;
import am.techmock.shop.model.order.OrderItem;
import am.techmock.shop.model.order.OrderMetadata;
import am.techmock.shop.repository.OrderRepository;
import am.techmock.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;

	@Autowired
	public OrderController(
			OrderRepository orderRepository,
			ProductRepository productRepository
	) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}

	@GetMapping
	public List<Order> list() {
		return orderRepository.list();
	}

	@PostMapping()
	public void add(@RequestBody OrderMetadata orderMetadata) {

		var items = orderMetadata.items().stream().flatMap(item ->
				productRepository.getProductById(item.productId())
						.map(product -> new OrderItem(product, item.quantity()))
						.stream()
		).toList();

		var order = new Order(
				-1,
				items,
				orderMetadata.contact(),
				orderMetadata.price(),
				false
		);
		orderRepository.add(order);
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable int id) {
		orderRepository.remove(id);
	}
}
