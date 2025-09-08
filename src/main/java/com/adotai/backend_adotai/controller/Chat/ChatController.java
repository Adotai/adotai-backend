package com.adotai.backend_adotai.controller.Chat;

import com.adotai.backend_adotai.dto.Chat.ChatMessageDTO;
import com.adotai.backend_adotai.dto.Chat.ChatRoomDTO;
import com.adotai.backend_adotai.dto.Chat.Requests.ChatMessageRequest;
import com.adotai.backend_adotai.dto.Chat.Requests.ChatRoomRequest;
import com.adotai.backend_adotai.service.chat.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    // Criar novo chat
    @PostMapping("/room")
    public ResponseEntity<ChatRoomDTO> createChat(@RequestBody ChatRoomRequest request) {
        ChatRoomDTO chatRoom = chatService.createChat(request);
        return ResponseEntity.ok(chatRoom);
    }

    // Buscar chat por ID
    @GetMapping("/room/{id}")
    public ResponseEntity<ChatRoomDTO> getChatRoom(@PathVariable int id) {
        ChatRoomRequest request = new ChatRoomRequest(id, 0, 0); // userId e ongId não são necessários aqui
        ChatRoomDTO chatRoom = chatService.getChatRoom(request);
        return chatRoom != null ? ResponseEntity.ok(chatRoom) : ResponseEntity.notFound().build();
    }

    // Listar todos os chats de uma conta (user ou ong)
    @GetMapping("/rooms/account/{accountId}")
    public ResponseEntity<List<ChatRoomDTO>> getChatsByAccount(@PathVariable int accountId) {
        List<ChatRoomDTO> chats = chatService.getChatsByAccount(accountId);
        return ResponseEntity.ok(chats);
    }

    // Enviar mensagem
    @PostMapping("/message")
    public ResponseEntity<ChatMessageDTO> sendMessage(@RequestBody ChatMessageRequest request) {
        ChatMessageDTO message = chatService.sendMessage(request);
        return ResponseEntity.ok(message);
    }

    // Buscar mensagens de um chat
    @GetMapping("/messages/{chatId}")
    public ResponseEntity<List<ChatMessageDTO>> getMessages(@PathVariable int chatId) {
        ChatMessageRequest request = new ChatMessageRequest(chatId, 0, null, null); // senderId e contentType não usados aqui
        List<ChatMessageDTO> messages = chatService.getMessages(request);
        return ResponseEntity.ok(messages);
    }
}
