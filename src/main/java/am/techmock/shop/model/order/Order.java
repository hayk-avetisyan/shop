package am.techmock.shop.model.order;

import am.techmock.shop.model.OrderContact;

import java.util.List;

public record Order(
		int id,
		List<OrderItem> items,
		OrderContact contact,
		int price,
		boolean done
) {

}

