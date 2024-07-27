package pro.sky.telegrambot.dto;

import pro.sky.telegrambot.entities.NotificationTaskEntity;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class NotificationTaskDto {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static NotificationTaskDto convertEntityToDto(NotificationTaskEntity entity) {
        NotificationTaskDto dto = new NotificationTaskDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String date = entity.getSheduleDate().format(formatter);
        String message = date + " " + entity.getText();
        dto.setMessage(message);
        return dto;
    }
}
