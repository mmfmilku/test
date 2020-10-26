package design_mode.builder;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

public class OkhttpTest {

    @Test
    public void test0() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request0 = new Request.Builder()
                .header("User-Agent", "Chrome/85.0.4183.102 Safari/537.36")
                .url("https://www.bilibili.com")
                .get()
                .build();

        Request request = new Request.Builder()
                .header("User-Agent", "Chrome/85.0.4183.102 Safari/537.36")
                .url("https://www.bilibili.com")
                .get()
                .build();

        Response res = client.newCall(request)
                .execute();

        System.out.println(res.body().string());
    }

    @Test
    public void test1() {
        System.out.println(888);
    }

}
