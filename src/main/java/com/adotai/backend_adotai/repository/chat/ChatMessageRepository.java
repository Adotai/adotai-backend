package com.adotai.backend_adotai.repository.chat;

import com.adotai.backend_adotai.entity.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
    List<ChatMessage> findByChatIdOrderByCreatedAtAsc(int chatId);
    List<ChatMessage> findByChatId(int chatId);
}
