package my_package;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MyTelegramBot extends TelegramLongPollingBot {
     final String BOT_NAME;
     final String BOT_TOKEN;
    final String NASA_URL = "https://api.nasa.gov/planetary/apod" +
            "?api_key=7Dee7k51tEXObDg8pxJRIcNhpeid0sWL397uOO2s";

    public MyTelegramBot(String BOT_NAME, String BOT_TOKEN) throws TelegramApiException {
        this.BOT_NAME = BOT_NAME;
        this.BOT_TOKEN = BOT_TOKEN;

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        // TODO

        //  /help    /start  /image  /date 2023-12-20
        if (update.hasMessage() && update.getMessage().hasText()) {
            String[] separatedAction = update.getMessage().getText().split(" ");
            String action = separatedAction[0];
            long chatId = update.getMessage().getChatId();

            switch (action) {
                case "/help" :
                    sendNessage("Я бот НАСА, шлю картинку дня", chatId);
                    break;
                case "/start" :
                case"/image" :
                    String imageUrl = Utils.getUrl(NASA_URL);
                    sendNessage(imageUrl,chatId);
                    break;
                case "/date" :
                    String date = separatedAction[1];
                    imageUrl = Utils.getUrl(NASA_URL+"&date="+date);
                    sendNessage(imageUrl,chatId);
                    break;

                default:
                    sendNessage("Я тебя не понимаю",chatId);
                    break;
            }
        }
    }

    void sendNessage(String text, long chatId) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            System.out.println("Не отправилось сообщение");
        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        // TODO
        return BOT_TOKEN;
    }
}
