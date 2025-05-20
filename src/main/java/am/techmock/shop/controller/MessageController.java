package am.techmock.shop.controller;

import am.techmock.shop.model.Message;
import am.techmock.shop.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/messages")
public class MessageController {

	private final MessageRepository messageRepository;

	@Autowired
	public MessageController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@GetMapping
	public List<Message> list() {
		return messageRepository.list();
	}

	@PostMapping()
	public void add(@RequestBody Message message) {
		messageRepository.add(message);
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable int id) {
		messageRepository.remove(id);
	}

	@PatchMapping("/{id}/read")
	public void markAsRead(@PathVariable int id) {
		messageRepository.markAsRead(id);
	}

	@PatchMapping("/{id}/unread")
	public void markAsUnread(@PathVariable int id) {
		messageRepository.markAsUnread(id);
	}
}
