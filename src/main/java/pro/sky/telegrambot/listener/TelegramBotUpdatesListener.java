package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entities.NotificationTaskEntity;
import pro.sky.telegrambot.parser.NotificationTaskParser;
import pro.sky.telegrambot.repositories.NotificationTaskRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final NotificationTaskParser parser;
    private final TelegramBot telegramBot;

    private final NotificationTaskRepository repository;

    public TelegramBotUpdatesListener(NotificationTaskParser parser,
                                      TelegramBot telegramBot,
                                      NotificationTaskRepository repository) {
        this.parser = parser;
        this.telegramBot = telegramBot;
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if (update.message().text().equals("/start")) {
                var chatId = update.message().chat().id();
                setGreetingMessage(chatId);
            }
            if (update.message().text()
                    .matches("((^([0-2][0-9])|(3[0-1]))\\.((0[0-9])|(1[0-2]))\\.202[4-9])\\s(([0-1][0-9])|(2[0-3])):[0-5][0-9]\\s.+$")) {
                NotificationTaskEntity notificationTask = parser.parse(update);
                repository.save(notificationTask);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void setGreetingMessage(Long chatId) {
        String greetings = """
                      Доброго времени суток, добро пожаловать
                      в приложение, которое умеет напоминать о
                      поставленных задачах в заданное время.
                      Введите строку формата
                      "dd.MM.yyyy HH:mm Текст напоминания"
                      и данный бот напомнит Вам о поставленной задаче.
                """;
        SendMessage message = new SendMessage(chatId, greetings);
        SendResponse response = telegramBot.execute(message);
    }
}


