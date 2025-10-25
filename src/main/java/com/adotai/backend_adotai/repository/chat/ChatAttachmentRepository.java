package com.adotai.backend_adotai.repository.chat;

import com.adotai.backend_adotai.entity.chat.ChatAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatAttachmentRepository extends JpaRepository<ChatAttachment, Integer> {
    List<ChatAttachment> findByMessageId(int messageId);
}
