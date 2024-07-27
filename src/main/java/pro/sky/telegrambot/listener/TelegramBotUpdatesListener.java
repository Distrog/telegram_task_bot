package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

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
                SendMessage message = new SendMessage(chatId, "Приветствую");
                SendResponse response = telegramBot.execute(message);
            }
            if(update.message().text()
                    .matches("((^([0-2][0-9])|(3[0-1]))\\.((0[0-9])|(1[0-2]))\\.202[4-9])\\s(([0-1][0-9])|(2[0-3])):[0-5][0-9]\\s.+$")){
                var chatId = update.message().chat().id();
                SendMessage message = new SendMessage(chatId, "правильно введено");
                SendResponse response = telegramBot.execute(message);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}


