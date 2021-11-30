package entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Table(name = "links")
@Entity
public class Links {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne(cascade = CascadeType.PERSIST)

    private CryptoValuta cryptoValuta;
    @Expose
    private String link;
    @Expose
    private String exchange;

    public Links(CryptoValuta cryptoValuta, String link, String exchange) {
        this.cryptoValuta = cryptoValuta;
        this.link = link;
        this.exchange = exchange;
    }

    public Links() {
    }

    public CryptoValuta getCryptoValuta() {
        return cryptoValuta;
    }

    public void setCryptoValuta(CryptoValuta cryptoValuta) {
        if(cryptoValuta != null){
            this.cryptoValuta.removeLink(this);
        }
        this.cryptoValuta = cryptoValuta;
        cryptoValuta.addLink(this);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}