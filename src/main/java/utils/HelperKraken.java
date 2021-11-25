package utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Future;

public class HelperKraken {
    private Future<Map <String, Map<String, Map<String, ArrayList<String>>>>> kraken;
    private String exchange;
    private String name;

    public HelperKraken(Future<Map<String, Map<String, Map<String, ArrayList<String>>>>> kraken, String exchange, String name) {
        this.kraken = kraken;
        this.exchange = exchange;
        this.name = name;
    }

    public Future<Map<String, Map<String, Map<String, ArrayList<String>>>>> getKraken() {
        return kraken;
    }

    public void setKraken(Future<Map<String, Map<String, Map<String, ArrayList<String>>>>> kraken) {
        this.kraken = kraken;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
