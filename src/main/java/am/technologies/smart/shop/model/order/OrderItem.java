package am.technologies.smart.shop.model.order;

import am.technologies.smart.shop.model.Product;

public record OrderItem(
		Product product,
		int quantity
) {
}
