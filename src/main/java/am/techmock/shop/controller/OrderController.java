package am.techmock.shop.controller;

import am.techmock.shop.model.order.Order;
import am.techmock.shop.model.order.OrderItem;
import am.techmock.shop.model.order.OrderItemMetadata;
import am.techmock.shop.model.order.OrderMetadata;
import am.techmock.shop.repository.OrderRepository;
import am.techmock.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

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
	public void add(@RequestBody OrderMetadata metadata) {

		var items = metadata.items().stream().flatMap(this::orderItem).toList();
		var order = new Order(-1, items, metadata.contact(), metadata.price(), false);

		orderRepository.add(order);
	}

	@PatchMapping("/{id}/done")
	public void markAsDone(@PathVariable int id) {
		orderRepository.markAsDone(id);
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable int id) {
		orderRepository.remove(id);
	}

	private Stream<OrderItem> orderItem(OrderItemMetadata item) {
		return productRepository.getProductById(item.productId()).map(product -> new OrderItem(product, item.quantity()));
	}
}
