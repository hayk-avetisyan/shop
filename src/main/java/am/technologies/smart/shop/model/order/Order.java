package am.technologies.smart.shop.model.order;

import am.technologies.smart.shop.model.OrderContact;

import java.util.List;

public record Order(
		int id,
		List<OrderItem> items,
		OrderContact contact,
		int price,
		boolean done
) {

}

