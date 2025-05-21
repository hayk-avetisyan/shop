package am.techmock.shop.model;

import java.util.Map;

public record ProductCategory(
		int id,
		String title,
		String description,
		String coverImage,
		String coverVideo,
		Map<Integer, Product> products
) {
	static public ProductCategory of(
			int id, String title, String description,
			String coverImage, String coverVideo, Map<Integer, Product> products
	) {
		return new ProductCategory(id, title, description, coverImage, coverVideo, products);
	}
}
