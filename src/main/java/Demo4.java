import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

// OkHttp

public class Demo4 {

    public static void main(String[] args) throws IOException {

        // Экземпляр класса OkHttpClient выполняет всю работу по созданию и отправке запросов
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .build();

        // Экземпляр класса Request создается через Builder (см. паттерн проектирования "Строитель")
        Request request = new Request.Builder()
                .url("https://geekbrains.ru")
                .build();

        //  Другой способ собрать URL
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("geekbrains.ru")
                .addPathSegment("courses")
                //            .addQueryParameter("key", "value")
                .build();

        // Получение объекта ответа от сервера
        Response response = client.newCall(request).execute();

        // Тело сообщения возвращается методом body объекта Response
        String body = response.body().string();

        System.out.println(response.code());
        System.out.println(response.headers());
        System.out.println(response.isRedirect());
        System.out.println(response.isSuccessful());
        System.out.println(response.protocol());
        System.out.println(response.receivedResponseAtMillis());

        /*
        POST https://restful-booker.herokuapp.com/apidoc/index.html

        1. Для начала получим токен авторизации https://restful-booker.herokuapp.com/apidoc/index.html#api-Auth
        */
        MediaType JSON = MediaType.parse("application/json");


        String authBodyString = "{" +
                "\"username\": \"admin\", " +
                "\"password\": \"password123\"" +
                "}";
        System.out.println(authBodyString);

        RequestBody authBody = RequestBody.create(authBodyString, JSON);

        request = new Request.Builder()
                .url("https://restful-booker.herokuapp.com/auth")
                //            .header("Content-type", "application/json")
                .post(authBody)
                .build();

        Response responseWithToken = client.newCall(request).execute();
        String responseBody = responseWithToken.body().string();

        System.out.println(responseBody);

        // Распарсим строку чтобы взять токен (показать боль работы с json, чтобы на следующем занятии показать jackson)
        String token = responseBody.split(":")[1];
        token = token.replaceAll("[^\\p{L}\\p{Nd}]+", "");

        System.out.println(token);

        /*
        2. Создание бронирования (токен авторизации не обязателен) https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-CreateBooking
        */

        String jsonBody = "{" +
                "\"firstname\" : \"Jim\"," +
                "\"lastname\" : \"Brown\"," +
                "\"totalprice\" : 111, " +
                "\"depositpaid\" : true," +
                "\"bookingdates\" : {" +
                "\"checkin\" : \"2018-01-01\"," +
                "\"checkout\" : \"2019-01-01\" }, " +
                "\"additionalneeds\" : \"Breakfast\" }";


        RequestBody createBookingBody = RequestBody.create(jsonBody, MediaType.parse("application/json"));
        request = new Request.Builder()
                .url("https://restful-booker.herokuapp.com/booking")
                .addHeader("accept", "application/json") // <- без этого не будет работать, в доках нет.
                .post(createBookingBody)
                .build();

        response = client.newCall(request).execute();

        System.out.println(response.headers());
        System.out.println(response.body().string());
    }

}
