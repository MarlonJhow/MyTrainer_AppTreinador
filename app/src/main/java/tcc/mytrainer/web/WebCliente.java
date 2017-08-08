package tcc.mytrainer.web;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Marlon on 04/08/2017.
 */

public class WebCliente extends AsyncTask<String,Void,String>{

    private static final String endpoint = "http://5982fe8c34e4a300116fea78.mockapi.io/rest/";

    public WebCliente() {
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String url = endpoint + params[0];
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = null;
            response = client.newCall(request).execute();
            String jsonDeResposta = response.body().string();
            return jsonDeResposta;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
