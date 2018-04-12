package training.javabrains.facebook.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import training.javabrains.facebook.data.Message;
import training.javabrains.facebook.database.DatabaseClass;
import training.javabrains.facebook.exception.DataNotFoundException;

public class MessageService {

	private static Map<Long, Message> messageMap = DatabaseClass.getMessageMap(); // Fake database

	public MessageService() {
	}

	// So it can only be created once.
	static {
		messageMap.put(1L, new Message(1, "RESTful", "Emma"));
		messageMap.put(2L, new Message(2, "SOAP", "Roni"));
	}

	public List<Message> getAllMessages() {

		return new ArrayList<>(messageMap.values());
	}

	public List<Message> getAllMessagesForYear(int year) {

		List<Message> messageList = new ArrayList<>();

		Calendar cal = Calendar.getInstance();
		for (Message message : this.getAllMessages()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageList.add(message);
			}
		}

		return messageList;
	}

	/**
	 * Paging
	 */
	public List<Message> getAllMessagesPagingnated(int start, int size) {

		List<Message> messageList = new ArrayList<>(messageMap.values());
		if (start + size > messageList.size()) {

			return new ArrayList<Message>();
		}

		return messageList.subList(start, start + size);
	}

	public Message getMessage(long id) {

		Message message = messageMap.get(id);

		if (message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found!");
		}
		
		return message;
	}

	public Message addMessage(Message message) {
		message.setId(messageMap.size() + 1);
		messageMap.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}

		messageMap.put(message.getId(), message);
		return message;

	}

	public Message removeMessage(long id) {
		return messageMap.remove(id);
	}
}
