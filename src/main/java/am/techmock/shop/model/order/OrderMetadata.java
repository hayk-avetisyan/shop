package am.techmock.shop.model.order;

import am.techmock.shop.model.OrderContact;

import java.util.List;

public record OrderMetadata(
		List<OrderItemMetadata> items,
		OrderContact contact,
		int price
) {
}

