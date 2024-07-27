package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entities.NotificationTaskEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationTaskRepository extends JpaRepository<NotificationTaskEntity,Long> {
    public List<NotificationTaskEntity> findBySheduleDate(LocalDateTime sheduleDate);
}