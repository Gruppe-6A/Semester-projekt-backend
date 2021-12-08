package utils;

import dtos.CryptoCombinedDTO;
import entities.PriceOverTime;
import facades.CryptoFacade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;

public class TimedEvent {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    static CryptoFacade crypto = CryptoFacade.getCryptoFacade(emf);


    public static void imnotsure() {
        final ScheduledExecutorService scheduler =
                Executors.newScheduledThreadPool(1);

        final Runnable beeper = new Runnable() {
            public void run() {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.set(Calendar.SECOND, 0);
                try {
                    List<PriceOverTime> PoTList = new ArrayList<>();
                    List<CryptoCombinedDTO> cryptos = HttpUtils.fetchcryptos(crypto.getCryptoFromDB());
                    for (CryptoCombinedDTO cryptos1 : cryptos) {
                        PoTList.add(new PriceOverTime(cryptos1.getName(), cryptos1.getPrice(), calendar));
                    }
                    crypto.putPriceIntoDB(PoTList);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 30, 30, SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
            }
        }, 60 * 60, SECONDS);
    }




}

