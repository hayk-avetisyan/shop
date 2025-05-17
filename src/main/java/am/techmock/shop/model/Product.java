package am.techmock.shop.model;

public record Product(
		int id,
		String title,
		String image,
		String description,
		int price
) {

	static public Product of(
			int id, String title, String image, String description, int price
	) {
		return new Product(id, title, image, description, price);
	}
}
