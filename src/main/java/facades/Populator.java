/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.CryptoValuta;
import entities.Links;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        CryptoValuta Bitcoin = new CryptoValuta("bitcoin");
        CryptoValuta Ethereum = new CryptoValuta("ethereum");
        Links btcKraken = new Links(Bitcoin, "BTCUSD", "kraken");
        Links btcYobit = new Links(Bitcoin, "btc_usdt", "yobit");
        Links ethKraken = new Links(Ethereum, "ETHUSD", "kraken");
        Links ethYobit = new Links(Ethereum, "eth_usdt", "yobit");
        Ethereum.addLink(ethKraken);
        Ethereum.addLink(ethYobit);
        Bitcoin.addLink(btcKraken);
        Bitcoin.addLink(btcYobit);
        em.getTransaction().begin();
        em.persist(Bitcoin);
        em.persist(Ethereum);
        em.getTransaction().commit();
        em.close();

    }
    
    public static void main(String[] args) {
        populate();
    }
}
