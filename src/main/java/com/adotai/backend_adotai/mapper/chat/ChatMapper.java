package com.adotai.backend_adotai.mapper.chat;

import com.adotai.backend_adotai.dto.Chat.ChatAttachmentDTO;
import com.adotai.backend_adotai.dto.Chat.ChatMessageDTO;
import com.adotai.backend_adotai.dto.Chat.ChatReceiptDTO;
import com.adotai.backend_adotai.dto.Chat.ChatRoomDTO;
import com.adotai.backend_adotai.entity.chat.ChatAttachment;
import com.adotai.backend_adotai.entity.chat.ChatMessage;
import com.adotai.backend_adotai.entity.chat.ChatReceipt;
import com.adotai.backend_adotai.entity.chat.ChatRoom;

import java.util.stream.Collectors;

public class ChatMapper {

    public static ChatRoomDTO toDTO(ChatRoom room) {
        if (room == null) return null;
        ChatRoomDTO dto = new ChatRoomDTO();
        dto.setId(room.getId());
        dto.setUserId(room.getUser().getId());
        dto.setOngId(room.getOng().getId());
        dto.setCreatedAt(room.getCreatedAt());
        dto.setUpdatedAt(room.getUpdatedAt());
        dto.setMessages(room.getMessages() != null ?
                room.getMessages().stream().map(ChatMapper::toDTO).collect(Collectors.toList()) : null);
        return dto;
    }

    public static ChatMessageDTO toDTO(ChatMessage msg) {
        if (msg == null) return null;
        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setId(msg.getId());
        dto.setChatId(msg.getChat().getId());
        dto.setSenderId(msg.getSender().getId());
        dto.setContent(msg.getContent());
        dto.setContentType(msg.getContentType());
        dto.setCreatedAt(msg.getCreatedAt());
        dto.setEditedAt(msg.getEditedAt());
        dto.setDeletedAt(msg.getDeletedAt());
        dto.setAttachments(msg.getAttachments() != null ?
                msg.getAttachments().stream().map(ChatMapper::toDTO).collect(Collectors.toList()) : null);
        dto.setReceipts(msg.getReceipts() != null ?
                msg.getReceipts().stream().map(ChatMapper::toDTO).collect(Collectors.toList()) : null);
        return dto;
    }

    public static ChatAttachmentDTO toDTO(ChatAttachment att) {
        if (att == null) return null;
        ChatAttachmentDTO dto = new ChatAttachmentDTO();
        dto.setId(att.getId());
        dto.setMessageId(att.getMessage().getId());
        dto.setUrl(att.getUrl());
        dto.setMimeType(att.getMimeType());
        dto.setWidth(att.getWidth());
        dto.setHeight(att.getHeight());
        dto.setSizeBytes(att.getSizeBytes());
        return dto;
    }

    public static ChatReceiptDTO toDTO(ChatReceipt receipt) {
        if (receipt == null) return null;
        ChatReceiptDTO dto = new ChatReceiptDTO();
        dto.setMessageId(receipt.getMessage().getId());
        dto.setSenderId(receipt.getSender().getId());
        dto.setStatus(receipt.getStatus());
        dto.setDeliveredAt(receipt.getDeliveredAt());
        dto.setReadAt(receipt.getReadAt());
        return dto;
    }
}
