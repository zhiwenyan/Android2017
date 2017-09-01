package zhiwenyan.cmccaifu.com.android2017.okhttp;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhiwenyan on 4/19/17.
 */

/**
 * 缓存案例
 */
public final class CacheResponse {
    private final OkHttpClient client;
    public static final String URL = "http://pb9.pstatp.com/origin/24990000d4c26180d691";

    public CacheResponse(File cacheDirectory) throws Exception {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(cacheDirectory, cacheSize);

        client = new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url(URL)
                .build();

        String response1Body;
        try (Response response1 = client.newCall(request).execute()) {
            if (!response1.isSuccessful()) throw new IOException("Unexpected code " + response1);

            response1Body = response1.body().string();
            System.out.println("Response 1 response:          " + response1);
            System.out.println("Response 1 cache response:    " + response1.cacheResponse());
            System.out.println("Response 1 network response:  " + response1.networkResponse());
        }

        String response2Body;
        try (Response response2 = client.newCall(request).execute()) {
            if (!response2.isSuccessful()) throw new IOException("Unexpected code " + response2);

            response2Body = response2.body().string();
            System.out.println("Response 2 response:          " + response2);
            System.out.println("Response 2 cache response:    " + response2.cacheResponse());
            System.out.println("Response 2 network response:  " + response2.networkResponse());
        }

        System.out.println("Response 2 equals Response 1? " + response1Body.equals(response2Body));
    }

    public static void main(String... args) throws Exception {
        new CacheResponse(new File("a.png")).run();
    }
}
