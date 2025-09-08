package com.adotai.backend_adotai.entity.chat;

import com.adotai.backend_adotai.entity.Account;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "chat_message")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_id", nullable = false)
    private ChatRoom chat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private Account sender;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "content_type", nullable = false)
    private String contentType = "TEXT";

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "edited_at")
    private Instant editedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatAttachment> attachments;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatReceipt> receipts;

    // Getters e Setters
    public int getId() { return id; }
    public ChatRoom getChat() { return chat; }
    public void setChat(ChatRoom chat) { this.chat = chat; }
    public Account getSender() { return sender; }
    public void setSender(Account sender) { this.sender = sender; }
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
    public List<ChatAttachment> getAttachments() { return attachments; }
    public void setAttachments(List<ChatAttachment> attachments) { this.attachments = attachments; }
    public List<ChatReceipt> getReceipts() { return receipts; }
    public void setReceipts(List<ChatReceipt> receipts) { this.receipts = receipts; }
}
