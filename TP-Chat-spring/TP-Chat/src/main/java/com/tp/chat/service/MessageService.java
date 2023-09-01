package com.tp.chat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.chat.entity.Message;
import com.tp.chat.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;

	public Boolean addMessage(Message message) {
		if (message.getContent() != null && message.getDate() != null) {
			messageRepository.save(message);
			return true;
		} else {
			return false;
		}
	}

	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

	public Message getMessageById(Integer id) {
		Optional<Message> optionalMessage = messageRepository.findById(id);
		if (optionalMessage.isPresent()) {
			Message foundMessage = optionalMessage.get();
			return foundMessage;
		} else {
			return new Message();
		}
	}

	public Boolean deleteMessageById(Integer id) {
		Optional<Message> optionalMessage = messageRepository.findById(id);
		if (optionalMessage.isPresent()) {
			messageRepository.deleteById(id);
			;
			return true;
		} else
			return false;
	}

	public Boolean updateMessageById(Integer id, Message message) {
		Optional<Message> optionalMessage = messageRepository.findById(id);
		if (optionalMessage.isPresent() && message.getId() == id) {
			messageRepository.save(null);
			return true;
		} else {
			return false;
		}
	}

	public List<Message> getMessagesByCanalId(Integer canalId) {
		return messageRepository.findByCanalId(canalId);
	}
}
