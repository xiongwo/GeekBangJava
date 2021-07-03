package watcher.ciri.client;

import okhttp3.*;

import java.io.IOException;

public class ClientMain {

    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("http://localhost:8801").build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        ResponseBody body;
        if ((body = response.body()) != null) {
            System.out.println(body.string());
        }
    }

}
