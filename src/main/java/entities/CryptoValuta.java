package entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "crypto_valuta")
@Entity
public class CryptoValuta {
    @Id
    @Column(name = "id", nullable = false)
    @Expose
    private String id;
    @OneToMany(mappedBy = "cryptoValuta", cascade = CascadeType.PERSIST)

    private List<Links> linksList = new ArrayList<Links>();

    public CryptoValuta(String id) {
        this.id = id;
    }
    public CryptoValuta() {
    }



    public void addLink(Links link){
        linksList.add(link);
    }
    public void removeLink(Links link){
        linksList.remove(link);
    }


    public List<Links> getLinksList() {
        return linksList;
    }

    public void setLinksList(List<Links> linksList) {
        this.linksList = linksList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}