package com.adotai.backend_adotai.dto.Chat.Requests;

public record ChatMessageRequest(
        int chatRoomId,
        int senderId,
        String message,
        String contentType
) {
}
