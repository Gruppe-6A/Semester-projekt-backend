package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.*;
import entities.CryptoValuta;
import entities.Links;
import entities.User;
import entities.UserCrypto;
import utils.EMF_Creator;
import utils.HttpUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Link;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CryptoFacade {
    private static EntityManagerFactory emf;
    private static CryptoFacade instance;

    private CryptoFacade() {
    }

     Gson gson = new GsonBuilder().setPrettyPrinting().create();
    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static CryptoFacade getCryptoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CryptoFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<CryptoValutaDTO> getCryptoFromDB(){
        EntityManager em = getEntityManager();
        TypedQuery query =  em.createQuery("Select c from CryptoValuta c", CryptoValuta.class);
        List<CryptoValuta> cl = query.getResultList();

        return CryptoValutaDTO.getCryptoValutaDTO(cl);
    }
    public void addCrypto(CryptoValutaDTO cvDTO){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        CryptoValuta cryptoValuta = new CryptoValuta(cvDTO.getId());
        for(LinksDTO linksdto : cvDTO.getLinkList()){
            cryptoValuta.addLink(new Links(cryptoValuta, linksdto.getLink(), linksdto.getExchange()));
        }
        em.persist(cryptoValuta);
        em.getTransaction().commit();
        em.close();
    }
    public void addToPortfolio(UserCryptoList userCryptoList){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();

        for(UserCryptoDTO userCrypto : userCryptoList.getUserCryptoDTOList()){
                User user = new User(userCrypto.getUserDTO().getUserName());
                CryptoValuta cryptoValuta = new CryptoValuta(userCrypto.getCryptoValutaDTO().getId());
                UserCrypto userCrypto1 = new UserCrypto(user, cryptoValuta, userCrypto.getCount());
                em.persist(userCrypto1);
        }

        em.getTransaction().commit();
        em.close();
    }

    public List<UserCryptoDTO> showPortfolio(String userName){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery query = em.createQuery("Select u from UserCrypto u where u.user.userName = :username", UserCrypto.class);
            query.setParameter("username", userName);
            List<UserCrypto> uc = query.getResultList();
            //System.out.println(uc.get(0).getCryptoValuta());
             em.getTransaction().commit();
            return UserCryptoDTO.getUserCryptoDTO(uc);
        }
        finally {
            em.close();
        }
    }



    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        emf = EMF_Creator.createEntityManagerFactory();
        CryptoFacade cf = getCryptoFacade(emf);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(HttpUtils.fetchcryptos(cf.getCryptoFromDB())));


    }

}
