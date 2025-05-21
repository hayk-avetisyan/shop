package am.techmock.shop.repository;

import am.techmock.shop.model.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MessageRepository {

	private final Map<Integer, Message> messages = new ConcurrentHashMap<>();
	private final AtomicInteger idGenerator = new AtomicInteger(1);

	public List<Message> list() {
		return new ArrayList<>(messages.values());
	}

	public void add(Message message) {
		int id = idGenerator.getAndIncrement();
		Message messageWithId = new Message(
				id,
				message.name(),
				message.email(),
				message.message(),
				false
		);
		messages.put(id, messageWithId);
	}

	public void remove(int id) {
		messages.remove(id);
	}

	public void markAsRead(int id) {
		Message message = messages.get(id);
		if (message != null) {
			messages.put(id, new Message(
					message.id(),
					message.name(),
					message.email(),
					message.message(),
					true
			));
		}
	}

	public void markAsUnread(int id) {
		Message message = messages.get(id);
		if (message != null) {
			messages.put(id, new Message(
					message.id(),
					message.name(),
					message.email(),
					message.message(),
					false
			));
		}
	}
}
