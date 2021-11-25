package dtos;

import java.util.ArrayList;

public class YobitDTO {
    private String sell;

    public YobitDTO(String sell) {
        this.sell = sell;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }
}
