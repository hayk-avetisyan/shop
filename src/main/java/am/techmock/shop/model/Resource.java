package am.techmock.shop.model;

public record Resource(
		MediaType type,
		String url
) {

	public static Resource image(String url) {
		return new Resource(MediaType.IMAGE, url);
	}

	public static Resource video(String url) {
		return new Resource(MediaType.VIDEO, url);
	}
}
