package am.techmock.shop.model.order;

import am.techmock.shop.model.Product;

public record OrderItem(
		Product product,
		int quantity
) {
}
