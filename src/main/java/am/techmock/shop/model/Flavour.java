package am.techmock.shop.model;

public record Flavour(
		int id,
		String title,
		String image,
		String description
) {

	static public Flavour of(
			int id, String title, String image, String description
	) {
		return new Flavour(id, title, image, description);
	}
}
