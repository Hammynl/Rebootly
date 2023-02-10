package hammynl.rebootly.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PhoneNotifier {

    private static PhoneNotifier instance;

    private PhoneNotifier() {}

    public static PhoneNotifier getInstance() {
        if(instance == null)
        {
            instance = new PhoneNotifier();
        }
        return instance;
    }

    public void sendPhoneNotification(String message, String phoneNumber, String apiKey) {
        try {

            String link = "https://api.callmebot.com/whatsapp.php?phone=%s&text=%s&apikey=%s";
            String signalLink = "https://api.callmebot.com/signal/send.php?phone=31612382128&apikey=040333&text=This+is+a+test";
            URL url = new URL(String.format(link, phoneNumber, message, apiKey).replace(" ", "+"));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            new InputStreamReader(con.getInputStream());
            con.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
