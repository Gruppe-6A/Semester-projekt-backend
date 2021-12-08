package entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Table(name = "price_over_time")
@Entity
public class PriceOverTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String coinName;
    private String price;
    private Calendar calendar;


    public PriceOverTime(String coinName, String price,  Calendar calendar) {
        this.coinName = coinName;
        this.price = price;
        this.calendar = calendar;
    }

    public PriceOverTime() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}