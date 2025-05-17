package am.techmock.shop.model;

import java.util.List;

public record ProductCategory(
		int id,
		String title,
		String description,
		String coverImage,
		String coverVideo,
		List<Product> products
) {
	static public ProductCategory of(
			int id, String title, String description,
			String coverImage, String coverVideo, List<Product> products
	) {
		return new ProductCategory(id, title, description, coverImage, coverVideo, products);
	}
}
