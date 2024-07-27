package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entities.NotificationTaskEntity;

public interface NotificationTaskRepository extends JpaRepository<NotificationTaskEntity,Long> {
}