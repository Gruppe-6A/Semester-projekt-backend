package dtos;

public class CrypDTO {
    private String from;
    private String price;

    public CrypDTO(String price, String from) {
        this.from = from;
        this.price = price;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }
}
