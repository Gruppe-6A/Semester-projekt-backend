package dtos;

public class CryptoCombinedDTO {
    private String name;
    private String exchange;
    private String price;

    public CryptoCombinedDTO(String name, String exchange, String price) {
        this.name = name;
        this.exchange = exchange;
        this.price = price;
    }
}
