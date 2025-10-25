package com.adotai.backend_adotai.dto.Chat;

import java.time.Instant;
import java.util.List;

public class ChatMessageDTO {
    private int id;
    private int chatId;
    private int senderId;
    private String content;
    private String contentType;
    private Instant createdAt;
    private Instant editedAt;
    private Instant deletedAt;
    private List<ChatAttachmentDTO> attachments;
    private List<ChatReceiptDTO> receipts;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getChatId() { return chatId; }
    public void setChatId(int chatId) { this.chatId = chatId; }
    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getEditedAt() { return editedAt; }
    public void setEditedAt(Instant editedAt) { this.editedAt = editedAt; }
    public Instant getDeletedAt() { return deletedAt; }
    public void setDeletedAt(Instant deletedAt) { this.deletedAt = deletedAt; }
    public List<ChatAttachmentDTO> getAttachments() { return attachments; }
    public void setAttachments(List<ChatAttachmentDTO> attachments) { this.attachments = attachments; }
    public List<ChatReceiptDTO> getReceipts() { return receipts; }
    public void setReceipts(List<ChatReceiptDTO> receipts) { this.receipts = receipts; }
}
