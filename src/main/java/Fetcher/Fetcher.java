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
import utils.TimedEvent;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Fetcher {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CryptoFacade crypto = CryptoFacade.getCryptoFacade(emf);
       /* TimedEvent timer = new TimedEvent();
        timer.imnotsure(crypto);

        */
        crypto.changeCalculator();


        Gson gson = new GsonBuilder().setPrettyPrinting().create();


    }
}
