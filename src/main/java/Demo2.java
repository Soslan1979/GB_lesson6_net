import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

// Обзор основных методов класса URL
public class Demo2 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        URL url1 = new URL("https://geekbrains.ru/courses");
        URL url2 = new URL("https", "geekbrains.ru", 443, "/");


        System.out.println(url1.getFile());
        System.out.println(url1.getPath());
        System.out.println(url1.getHost());

        System.out.println("------");

        System.out.println(url2.getPort());
        System.out.println(url2.getProtocol());
        System.out.println(url2.getUserInfo());
        System.out.println(url2.getAuthority());

        // Рассказать что такое URI
        System.out.println(url2.toURI());
    }

}
