package com.adotai.backend_adotai.entity.chat;

import java.io.Serializable;
import java.util.Objects;

public class ChatReceiptId implements Serializable {
    private int message;
    private int sender;

    public ChatReceiptId() {}

    public ChatReceiptId(int message, int sender) {
        this.message = message;
        this.sender = sender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatReceiptId)) return false;
        ChatReceiptId that = (ChatReceiptId) o;
        return message == that.message && sender == that.sender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, sender);
    }
}
