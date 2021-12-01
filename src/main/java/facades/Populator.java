/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RenameMeDTO;
import dtos.UserCryptoList;
import dtos.UserDTO;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
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
        User user = em.find(User.class, "user");
        UserCrypto userCrypto = new UserCrypto(user, Bitcoin, 3);
        List<UserCrypto> userCryptoList = new ArrayList<>();
        userCryptoList.add(userCrypto);
        System.out.println(gson.toJson(userCryptoList));

        /*em.getTransaction().begin();
        em.persist(userCrypto);
        em.persist(Bitcoin);
        em.persist(Ethereum);
        em.getTransaction().commit();
        em.close();

         */



    }

    public static void main(String[] args) {
        populate();
    }
}
