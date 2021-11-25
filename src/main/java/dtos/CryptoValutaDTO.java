package dtos;

import entities.CryptoValuta;

import java.util.ArrayList;
import java.util.List;

public class CryptoValutaDTO {
    private String id;
    private List<LinksDTO> linkList;



    public CryptoValutaDTO(List<LinksDTO> linkList, String id) {
        this.linkList = linkList;
        this.id = id;
    }
    public CryptoValutaDTO(CryptoValuta cv){
        this.linkList = LinksDTO.getLinksDTO(cv.getLinksList());
        this.id = cv.getId();
    }

    public static List<CryptoValutaDTO> getCryptoValutaDTO(List<CryptoValuta> cv){
        List<CryptoValutaDTO> cvDTO = new ArrayList();
        cv.forEach(ces->cvDTO.add(new CryptoValutaDTO(ces)));
        return cvDTO;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LinksDTO> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<LinksDTO> linkList) {
        this.linkList = linkList;
    }
}
