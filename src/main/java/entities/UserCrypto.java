package entities;

import com.google.gson.annotations.Expose;
import dtos.UserCryptoDTO;

import javax.persistence.*;


@Table(name = "user_crypto")
@Entity
public class UserCrypto  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @Expose
    private User user;
    @ManyToOne
    @Expose
    private CryptoValuta cryptoValuta;
    @Expose
    private int count;


    public UserCrypto(User user, CryptoValuta cryptoValuta, int count) {

        this.user = user;
        this.cryptoValuta = cryptoValuta;
        this.count = count;
    }

    public UserCrypto() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CryptoValuta getCryptoValuta() {
        return cryptoValuta;
    }

    public void setCryptoValuta(CryptoValuta cryptoValuta) {
        this.cryptoValuta = cryptoValuta;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}