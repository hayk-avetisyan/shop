package am.technologies.smart.shop.model.order;

import am.technologies.smart.shop.model.OrderContact;

import java.util.List;

public record OrderMetadata(
		List<OrderItemMetadata> items,
		OrderContact contact,
		int price
) {
}

