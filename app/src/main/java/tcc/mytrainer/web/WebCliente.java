package tcc.mytrainer.web;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Marlon on 04/08/2017.
 */

public class WebCliente {

    private static final String endpoint = "http://5982fe8c34e4a300116fea78.mockapi.io/rest/";

    public WebCliente() {
    }

    public String get(String path) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(endpoint).build();

        Response response = client.newCall(request).execute();

        String jsonDeResposta = response.body().string();

        return jsonDeResposta;
    }

}
