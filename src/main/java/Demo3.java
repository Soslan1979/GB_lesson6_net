import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

// Обзор URLConnection, HttpURLConnection
public class Demo3 {

    public static void main(String[] args) throws IOException {

        URL url = new URL("https", "geekbrains.ru", 443, "/");

        // 1 метод - openStream
        InputStream in = url.openStream();
        new BufferedReader(new InputStreamReader(in, UTF_8))
                .lines()
                .forEach(System.out::println);

        // 2 метод - openConnection
        URLConnection urlConnection = url.openConnection();
        in = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, UTF_8));
        PrintWriter writer = new PrintWriter("geek.html");
        String current = null;
        while ((current = bufferedReader.readLine()) != null) {
            writer.println(current);
        }
        writer.close();
        bufferedReader.close();

        //HttpURLConnection
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.connect();

        System.out.println(httpURLConnection.getResponseCode()); // 200
        System.out.println(httpURLConnection.getResponseMessage()); // OK
        System.out.println(httpURLConnection.getRequestMethod()); // GET
        System.out.println(httpURLConnection.getConnectTimeout()); // 0

        // Получение заголовков
        Map<String, List<String>> headers = httpURLConnection.getHeaderFields();
        for (String k: headers.keySet()) {
            System.out.printf("Ключ: %s, значение: %s \n", k, headers.get(k));
        }
    }
}
