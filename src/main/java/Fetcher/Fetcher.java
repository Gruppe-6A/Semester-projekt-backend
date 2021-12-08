package Fetcher;
import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.gson.GsonBuilder;
import dtos.KrakenDTO;
import dtos.YobitDTO;
import facades.CryptoFacade;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Fetcher {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CryptoFacade crypto = CryptoFacade.getCryptoFacade(emf);
        HttpUtils.fetchcryptos(crypto.getCryptoFromDB());


        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        Map <String, Map<String, String>>larshansen = gson.fromJson(HttpUtils.fetchData("https://yobit.net/api/2/btc_usdt/ticker"), Map.class);

        YobitDTO frederikke = null;
        for(Map tobias : larshansen.values()){
            String kulmule = gson.toJson(tobias);
            frederikke = gson.fromJson(kulmule, YobitDTO.class);

        }

        Map <String, Map<String, Map<String, ArrayList<String>>>>karljohan = gson.fromJson(HttpUtils.fetchData("https://api.kraken.com/0/public/Ticker?pair=BTCUSD"), Map.class);

        KrakenDTO pøllerkarl = null;
        for(Map mogens : karljohan.get("result").values()){
            String anusmand = gson.toJson(mogens);

            pøllerkarl = gson.fromJson(anusmand, KrakenDTO.class);
        }

    }
}
