package Fetcher;
import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;
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
        crypto.PoTList();


    }



   }

