package pro.sky.telegrambot.parser;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.entities.NotificationTaskEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class NotificationTaskParser {
    public NotificationTaskEntity parse(Update update) {
        NotificationTaskEntity task = new NotificationTaskEntity();
        String date = update.message().text().substring(0, 16);
        task.setSheduleDate(LocalDateTime
                .parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        String text = update.message().text().substring(17);
        task.setChatId(update.message().chat().id());
        task.setText(text);
        return task;
    }
}
