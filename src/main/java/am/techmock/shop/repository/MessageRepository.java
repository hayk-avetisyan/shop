package am.techmock.shop.repository;

import am.techmock.shop.model.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MessageRepository {
	private final List<Message> messages = new ArrayList<>();
	private final AtomicInteger idGenerator = new AtomicInteger(1);

	public List<Message> list() {
		return new ArrayList<>(messages);
	}

	public void add(Message message) {
		Message messageWithId = new Message(
				idGenerator.getAndIncrement(),
				message.name(),
				message.email(),
				message.message(),
				false
		);
		messages.add(messageWithId);
	}

	public void remove(int id) {
		messages.removeIf(message -> message.id() == id);
	}
}
