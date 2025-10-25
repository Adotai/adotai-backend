package com.adotai.backend_adotai.repository.chat;

import com.adotai.backend_adotai.entity.chat.ChatReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatReceiptRepository extends JpaRepository<ChatReceipt, Integer> {
    List<ChatReceipt> findByMessageId(int messageId);
}
