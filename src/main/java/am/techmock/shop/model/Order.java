package am.techmock.shop.model;

import java.util.List;

public record Order(
		int id,
		List<OrderItem> items,
		OrderContact contact,
		int price,
		boolean done
) {

}

