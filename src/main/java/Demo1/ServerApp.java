package Demo1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.time.ZoneId;

public class ServerApp {

    // Серверная часть чата
    public static void main(String[] args) {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(6666)) {
                Socket client = serverSocket.accept(); // Сервер вызывает метод accept() класса ServerSocket. Этот метод ожидает,
                // пока клиент не подключится к серверу по указанному порту и когда это происходит, возвращает объект типа Socket,
                // то есть воссозданный клиентский сокет.
                // И вот когда сокет клиента создан на стороне сервера, можно начинать двухстороннее общение.
                System.out.println("Client connected!");
                DataInputStream in = new DataInputStream(client.getInputStream());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                while (true) {
                    String clientResponse = in.readUTF();
                    System.out.println("Client response: " + clientResponse);
                    out.writeUTF("Сервер получил запрос и ответил Вам. Вы отправляли: " + clientResponse + " " +
                            Instant.now().atZone(ZoneId.of("Europe/Moscow")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
