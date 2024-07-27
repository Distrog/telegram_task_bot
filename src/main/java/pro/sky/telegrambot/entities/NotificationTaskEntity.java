package pro.sky.telegrambot.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification_task")
public class NotificationTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "chat_id")
    Long chatId;

    @Column(name = "message_text")
    String text;

    @Column(name = "shedule_date")
    LocalDateTime sheduleDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getSheduleDate() {
        return sheduleDate;
    }

    public void setSheduleDate(LocalDateTime sheduleDate) {
        this.sheduleDate = sheduleDate;
    }
}
