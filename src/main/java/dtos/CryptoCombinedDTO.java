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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
