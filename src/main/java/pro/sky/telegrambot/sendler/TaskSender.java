package pro.sky.telegrambot.sendler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.dto.NotificationTaskDto;
import pro.sky.telegrambot.entities.NotificationTaskEntity;
import pro.sky.telegrambot.repositories.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class TaskSender {
    private final TelegramBot bot;
    private final NotificationTaskRepository repository;

    public TaskSender(TelegramBot bot,
                      NotificationTaskRepository repository) {
        this.bot = bot;
        this.repository = repository;
    }

    @Scheduled(cron = "0 * * * * *")
    public void send() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<NotificationTaskEntity> notificationTasks = repository.findBySheduleDate(now);
        notificationTasks.stream().forEach(e -> {
            SendMessage message = new SendMessage(e.getChatId(), NotificationTaskDto.convertEntityToDto(e).getMessage());
            SendResponse response = bot.execute(message);
        });
    }
}

