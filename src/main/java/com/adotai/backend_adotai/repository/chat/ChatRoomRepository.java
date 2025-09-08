package com.adotai.backend_adotai.repository.chat;

import com.adotai.backend_adotai.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    Optional<ChatRoom> findByUserIdAndOngId(int userId, int ongId);

    @Query("SELECT c FROM ChatRoom c WHERE c.user.id = :accountId OR c.ong.id = :accountId")
    List<ChatRoom> findByAccountId(@Param("accountId") int accountId);
}
