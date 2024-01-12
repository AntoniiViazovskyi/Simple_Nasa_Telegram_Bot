package my_package;

import java.io.FileOutputStream;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {
        new MyTelegramBot("MyExperimentalFirstbot",
                "6627089786:AAH9Ql2Tjwhrb9BXQqOaa_KTIru6gb_qE1Y");


    }
}
