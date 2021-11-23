package dtos;

import java.util.ArrayList;

public class CombinedDTO {
    private TickerDTO ticker;
    private JokeDTO joke;


    public CombinedDTO(TickerDTO ticker, JokeDTO joke) {
        this.ticker = ticker;
        this.joke = joke;
    }
}
