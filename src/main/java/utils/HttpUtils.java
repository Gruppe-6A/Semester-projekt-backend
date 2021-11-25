package utils;
import com.google.gson.Gson;
import dtos.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpUtils {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        fetchDataParallel();
    }

    private static Gson gson = new Gson();
    public static TickerDTO fetchDataParallel() throws IOException, MalformedURLException, ExecutionException, InterruptedException
    {  ExecutorService es = Executors.newCachedThreadPool();
        Future<TickerDTO> bitcoinFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=BTC-USD"), TickerDTO.class));
        Future<TickerDTO> ethereumFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=ETH-USD"), TickerDTO.class));
        Future<TickerDTO> dogeFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=DOGE-BTC"), TickerDTO.class));
        Future<TickerDTO> litecoinFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=LTC-BTC"), TickerDTO.class));
        Future<TickerDTO> rippleFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=XRP-BTC"), TickerDTO.class));


        TickerDTO combinedTicker = new TickerDTO(new ArrayList<CrypDTO>());


        combinedTicker.addTicker(bitcoinFuture.get().getTickers().get(0));
        combinedTicker.addTicker(ethereumFuture.get().getTickers().get(0));
        combinedTicker.addTicker(dogeFuture.get().getTickers().get(0));
        combinedTicker.addTicker(litecoinFuture.get().getTickers().get(0));
        combinedTicker.addTicker(rippleFuture.get().getTickers().get(0));


        return combinedTicker;

    }
    public static CombinedDTO combinedEnds()throws IOException, MalformedURLException, ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        Future<TickerDTO> bitcoinFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=BTC-USD"), TickerDTO.class));
        Future<TickerDTO> ethereumFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=ETH-USD"), TickerDTO.class));
        Future<TickerDTO> dogeFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=DOGE-BTC"), TickerDTO.class));
        Future<TickerDTO> litecoinFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=LTC-BTC"), TickerDTO.class));
        Future<TickerDTO> rippleFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=XRP-BTC"), TickerDTO.class));
        Future<JokeDTO> jokeFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.chucknorris.io/jokes/random"), JokeDTO.class));


        TickerDTO combinedTicker = new TickerDTO(new ArrayList<CrypDTO>());


        combinedTicker.addTicker(bitcoinFuture.get().getTickers().get(0));
        combinedTicker.addTicker(ethereumFuture.get().getTickers().get(0));
        combinedTicker.addTicker(dogeFuture.get().getTickers().get(0));
        combinedTicker.addTicker(litecoinFuture.get().getTickers().get(0));
        combinedTicker.addTicker(rippleFuture.get().getTickers().get(0));

        JokeDTO jokes = jokeFuture.get();
        CombinedDTO combinedDTO = new CombinedDTO(combinedTicker, jokes);

        return combinedDTO;
    }

    public static List<CryptoCombinedDTO> fetchcryptos(List<CryptoValutaDTO> cvdto) throws IOException, ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        List<HelperMethods> hm = new ArrayList<>();
        List<HelperKraken> hk = new ArrayList<>();

        for(CryptoValutaDTO cvdto2 : cvdto){
            for(LinksDTO ldto : cvdto2.getLinkList()){
                if(ldto.getExchange().equals("yobit")){
                    hm.add(new HelperMethods(es.submit(
                        () -> gson.fromJson(HttpUtils.fetchData("https://yobit.net/api/2/" + ldto.getLink() + "/ticker"), Map.class)), ldto.getExchange(), cvdto2.getId()));

                } else if(ldto.getExchange().equals("kraken")){
                    hk.add(new HelperKraken(es.submit(
                        () -> gson.fromJson(HttpUtils.fetchData("https://api.kraken.com/0/public/Ticker?pair="+ldto.getLink()), Map.class)), ldto.getExchange(), cvdto2.getId()));

                }
            }
        }
        List<CryptoCombinedDTO> combined = new ArrayList<>();
       for(HelperMethods help : hm){
           for (Map helpmap : help.getYobit().get().values()) {
               String yobitprice = gson.toJson(helpmap);
               YobitDTO yobitdto = gson.fromJson(yobitprice, YobitDTO.class);
               combined.add(new CryptoCombinedDTO(help.getName(), help.getExchange(), yobitdto.getSell()));

           }
       }
        for(HelperKraken crack : hk){
            for(Map crackmap : crack.getKraken().get().get("result").values()) {
                String krakenprice = gson.toJson(crackmap);
                KrakenDTO krakendto = gson.fromJson(krakenprice, KrakenDTO.class);
                combined.add(new CryptoCombinedDTO(crack.getName(), crack.getExchange(), krakendto.getA().get(0)));
            }
        }



        System.out.println(gson.toJson(combined));
       return combined;
    }

    public static JokeDTO fetchJoke() throws IOException {
        String chuck = HttpUtils.fetchData("https://api.chucknorris.io/jokes/random");
        JokeDTO JokeDTO = gson.fromJson(chuck, JokeDTO.class);
        return JokeDTO;
    }
    public static String fetchData(String _url) throws MalformedURLException, IOException{
        URL url = new URL(_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");con.setRequestMethod("GET");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }
    }

