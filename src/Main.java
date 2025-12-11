import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class Main{
    public static void main(String[] args){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.tomorrow.io/v4/weather/forecast?location=new%20york&apikey=SG2RiSUy8OoG0Yxl8hqgwyFAPEkFcgVK")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("accept-encoding", "deflate, gzip, br")
                .build();

        Response response = client.newCall(request).execute();
    }
}