package my_package;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;

public class Utils {
    public static String getUrl(String nasaURL) {
        ObjectMapper mapper = new ObjectMapper();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(nasaURL);

        try {
            CloseableHttpResponse responce = client.execute(request);
            NasaAnswer answer = mapper.readValue(responce.getEntity().getContent(), NasaAnswer.class);
            return answer.url;
        } catch (IOException e) {
            System.out.println("Ошибка");
        }

        return "";

    }
}
