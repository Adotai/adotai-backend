package com.adotai.backend_adotai.entity.chat;

import com.adotai.backend_adotai.entity.Account;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "chat_receipt")
@IdClass(ChatReceiptId.class)
public class ChatReceipt {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "message_id", nullable = false)
    private ChatMessage message;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private Account sender;

    @Column(length = 12, nullable = false)
    private String status;

    @Column(name = "delivered_at")
    private Instant deliveredAt;

    @Column(name = "read_at")
    private Instant readAt;

    // Getters e Setters
    public ChatMessage getMessage() { return message; }
    public void setMessage(ChatMessage message) { this.message = message; }
    public Account getSender() { return sender; }
    public void setSender(Account sender) { this.sender = sender; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Instant getDeliveredAt() { return deliveredAt; }
    public void setDeliveredAt(Instant deliveredAt) { this.deliveredAt = deliveredAt; }
    public Instant getReadAt() { return readAt; }
    public void setReadAt(Instant readAt) { this.readAt = readAt; }
}
