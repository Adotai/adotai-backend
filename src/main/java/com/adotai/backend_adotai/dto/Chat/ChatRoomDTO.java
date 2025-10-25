package com.adotai.backend_adotai.dto.Chat;

import java.time.Instant;
import java.util.List;

public class ChatRoomDTO {
    private int id;
    private int userId;
    private int ongId;
    private Instant createdAt;
    private Instant updatedAt;
    private List<ChatMessageDTO> messages;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getOngId() { return ongId; }
    public void setOngId(int ongId) { this.ongId = ongId; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
    public List<ChatMessageDTO> getMessages() { return messages; }
    public void setMessages(List<ChatMessageDTO> messages) { this.messages = messages; }
}