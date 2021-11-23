package dtos;

import java.util.ArrayList;

public class TickerDTO {
    private ArrayList<CrypDTO> tickers;

    public TickerDTO(ArrayList<CrypDTO> tickers) {
        this.tickers = tickers;
    }

    public ArrayList<CrypDTO> getTickers() {
        return tickers;
    }

    public void setTickers(ArrayList<CrypDTO> tickers) {
        this.tickers = tickers;
    }

    public void addTicker(CrypDTO crypDTO){
        this.tickers.add(crypDTO);
    }
}
