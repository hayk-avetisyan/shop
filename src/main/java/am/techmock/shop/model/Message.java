package am.techmock.shop.model;

public record Message(
		int id,
		String name,
		String email,
		String message,
		boolean seen
) {
}
