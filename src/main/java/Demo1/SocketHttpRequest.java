package Demo1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketHttpRequest {

    // Демонстрация HTTP запроса (упрощенная, без хэдеров)
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("www.nyu.edu", 80);

        InputStream response = socket.getInputStream();  // входящие данные
        OutputStream request = socket.getOutputStream(); // выходные данные (отправляем на сервер)

        byte[] bytes = ("GET /community.html HTTP/1.0\n"
                + "Host: www.nyu.edu\n\n").getBytes(); // принимающий поток ждет указатель конца строки – «\n»,
        // иначе сообщение не будет принято, так как фактически сообщение не окончено, и не является целым.

        request.write(bytes);

        int ch;
        while ((ch = response.read()) != -1) {
            System.out.print((char) ch);
        }

        socket.close();
    }
}
