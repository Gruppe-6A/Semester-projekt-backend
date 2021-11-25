package utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Future;

public class HelperMethods {
    private Future<Map <String, Map<String, String>>> yobit;

    private String exchange;
    private String name;
    public HelperMethods(Future<Map<String, Map<String, String>>> yobit, String exchange, String name) {
        this.yobit = yobit;
        this.exchange = exchange;
        this.name = name;
    }

    public Future<Map<String, Map<String, String>>> getYobit() {
        return yobit;
    }

    public void setYobit(Future<Map<String, Map<String, String>>> yobit) {
        this.yobit = yobit;
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
