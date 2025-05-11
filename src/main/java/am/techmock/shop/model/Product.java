package am.techmock.shop.model;

import java.util.List;

public record Product(
		int id,
		String title,
		String description,
		Resource cover,
		Resource background,
		int price,
		List<Flavour> flavours
) {
	static public Product of(
			int id, String title, String description,
			Resource cover, Resource background, int price, List<Flavour> flavours
	) {
		return new Product(id, title, description, cover, background, price, flavours);
	}
}
