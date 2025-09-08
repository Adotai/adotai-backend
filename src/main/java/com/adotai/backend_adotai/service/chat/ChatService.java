package com.adotai.backend_adotai.service.chat;

import com.adotai.backend_adotai.dto.Chat.ChatAttachmentDTO;
import com.adotai.backend_adotai.dto.Chat.ChatMessageDTO;
import com.adotai.backend_adotai.dto.Chat.ChatReceiptDTO;
import com.adotai.backend_adotai.dto.Chat.ChatRoomDTO;
import com.adotai.backend_adotai.dto.Chat.Requests.ChatMessageRequest;
import com.adotai.backend_adotai.dto.Chat.Requests.ChatRoomRequest;
import com.adotai.backend_adotai.entity.Account;
import com.adotai.backend_adotai.entity.chat.ChatAttachment;
import com.adotai.backend_adotai.entity.chat.ChatMessage;
import com.adotai.backend_adotai.entity.chat.ChatReceipt;
import com.adotai.backend_adotai.entity.chat.ChatRoom;
import com.adotai.backend_adotai.mapper.chat.ChatMapper;
import com.adotai.backend_adotai.repository.AccountRepository;
import com.adotai.backend_adotai.repository.chat.ChatAttachmentRepository;
import com.adotai.backend_adotai.repository.chat.ChatMessageRepository;
import com.adotai.backend_adotai.repository.chat.ChatReceiptRepository;
import com.adotai.backend_adotai.repository.chat.ChatRoomRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatAttachmentRepository chatAttachmentRepository;
    private final ChatReceiptRepository chatReceiptRepository;
    private final AccountRepository accountRepository;

    public ChatService(ChatRoomRepository chatRoomRepository,
                       ChatMessageRepository chatMessageRepository,
                       ChatAttachmentRepository chatAttachmentRepository,
                       ChatReceiptRepository chatReceiptRepository,
                       AccountRepository accountRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.chatAttachmentRepository = chatAttachmentRepository;
        this.chatReceiptRepository = chatReceiptRepository;
        this.accountRepository = accountRepository;
    }


    //CRIAR UM CHAT
    @Transactional
    public ChatRoomDTO createChat(ChatRoomRequest dto) {
        // Evitar duplicidade
        Optional<ChatRoom> existing = chatRoomRepository.findByUserIdAndOngId(dto.userId(), dto.ongId());
        if (existing.isPresent()) {
            return ChatMapper.toDTO(existing.get());
        }

        // Buscar accounts
        Account user = accountRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Account ong = accountRepository.findById(dto.ongId())
                .orElseThrow(() -> new RuntimeException("Ong not found"));

        // Criar chat
        ChatRoom room = new ChatRoom();
        room.setUser(user);
        room.setOng(ong);

        room = chatRoomRepository.save(room);

        return ChatMapper.toDTO(room);
    }


     //Buscar chat por id
     @Transactional(readOnly = true)
     public ChatRoomDTO getChatRoom(ChatRoomRequest dto) {
         return chatRoomRepository.findById(dto.id())
                 .map(ChatMapper::toDTO)
                 .orElse(null);
     }

    // Listar todos os chats de um usuário
    @Transactional(readOnly = true)
    public List<ChatRoomDTO> getChatsByAccount(int accountId) {
        List<ChatRoom> rooms = chatRoomRepository.findByAccountId(accountId);
        return rooms.stream().map(ChatMapper::toDTO).collect(Collectors.toList());
    }


    // Criar nova mensagem
    @Transactional
    public ChatMessageDTO sendMessage(ChatMessageRequest dto) {
        // Buscar o chat
        ChatRoom room = chatRoomRepository.findById(dto.chatRoomId())
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        // Buscar a conta do remetente
        Account sender = accountRepository.findById(dto.senderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        if (room.getUser().getId() != sender.getId() &&
                room.getOng().getId() != sender.getId()) {
            throw new RuntimeException("Sender is not part of this chat room");
        }

        // Criar a mensagem
        ChatMessage msg = new ChatMessage();
        msg.setChat(room);
        msg.setSender(sender); // <-- aqui usamos Account
        msg.setContent(dto.message());
        msg.setContentType(dto.contentType() != null ? dto.contentType() : "TEXT");

        msg = chatMessageRepository.save(msg);

        return ChatMapper.toDTO(msg);
    }


    // Buscar mensagens de um chat
    @Transactional(readOnly = true)
    public List<ChatMessageDTO> getMessages(ChatMessageRequest dto) {
        List<ChatMessage> messages = chatMessageRepository.findByChatId(dto.chatRoomId());
        return messages.stream().map(ChatMapper::toDTO).collect(Collectors.toList());
    }

}