import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public class Accuweather_HW {

    private static final String BASE_HOST = "dataservice.accuweather.com"; // 2t6DLSzcOo184gM38d3Q5bIKZ2A8FsSH
    private static final String FORECAST = "forecasts";
    private static final String API_VERSION = "v1";
    private static final String FORECAST_TYPE = "daily";
    private static final String FORECAST_PERIOD = "5day";

    // private static final String SAINT_PETERSBURG_KEY = "474212_PC";
    // private static final String MOSCOW_KEY = "294021";
    private static final String VLADIKAVKAZ_KEY = "294391";
    private static final String API_KEY = "0d1tNZJPfzzT3qGokM18FGGxAUpt7hpj"; // 0d1tNZJPfzzT3qGokM18FGGxAUpt7hpj

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();

        // Сегментированное построение URL
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment(FORECAST)
                .addPathSegment(API_VERSION)
                .addPathSegment(FORECAST_TYPE)
                .addPathSegment(FORECAST_PERIOD)
                .addPathSegment(VLADIKAVKAZ_KEY)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("metric", "true")
                .build();

        System.out.println(url.toString());

        // При необходимости указать заголовки
        Request requesthttp = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();

        String jsonResponse = client.newCall(requesthttp).execute().body().string();
        System.out.println(jsonResponse);
    }
}
