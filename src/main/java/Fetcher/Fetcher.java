package Fetcher;
import com.google.gson.Gson;
import java.io.IOException;

import com.google.gson.GsonBuilder;
import com.nimbusds.jose.shaded.json.JSONObject;
import utils.HttpUtils;


public class Fetcher {
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String yep = HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=BTC-USD");
        System.out.println("JSON fetched from chucknorris:");
        System.out.println(yep);



    }
}
