package dtos;

import java.util.ArrayList;

public class KrakenDTO {
    private ArrayList<String> a;

    public KrakenDTO(ArrayList<String> a) {
        this.a = a;
    }

    public ArrayList<String> getA() {
        return a;
    }

    public void setA(ArrayList<String> a) {
        this.a = a;
    }
}
