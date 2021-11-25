package dtos;

import entities.CryptoValuta;
import entities.Links;

import java.util.ArrayList;
import java.util.List;

public class LinksDTO {
    private String link;
    private String exchange;

    public LinksDTO(String link, String exchange) {
        this.link = link;
        this.exchange = exchange;
    }
    public LinksDTO(Links link){
        this.link = link.getLink();
        this.exchange = link.getExchange();
    }

    public static List<LinksDTO> getLinksDTO(List<Links> l){
        List<LinksDTO> lDTO = new ArrayList();
        l.forEach(les->lDTO.add(new LinksDTO(les)));
        return lDTO;
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
}
