import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLContentReader {
    public static void main(String[] args) {
        String urlString = "https://www.youtube.com/watch?v=_3CQUTk9Gxs&themeRefresh=1";

        try {
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("HTTP Error: " + responseCode);
                return;
            }

            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }

            connection.disconnect();

            String contentString = content.toString();
            System.out.println("Content Length: " + contentString.length());
            System.out.println("First 500 Characters: " +
                    (contentString.length() > 500 ? contentString.substring(0, 500) : contentString));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
