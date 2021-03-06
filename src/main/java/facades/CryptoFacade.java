package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.*;
import entities.*;
import utils.EMF_Creator;
import utils.HttpUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Link;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

    public void putPriceIntoDB(List<PriceOverTime> PoT) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Collections.sort(PoT);
        ArrayList<String> list = new ArrayList<>();

        for(PriceOverTime PoT1 : PoT){
            if(!list.contains(PoT1.getCoinName())) {
                em.persist(PoT1);
                list.add(PoT1.getCoinName());
            }
        }
        em.getTransaction().commit();
        em.close();
    }
    public List<PriceOverTime> PoTList(Calendar calendar){
        EntityManager em = getEntityManager();


        TypedQuery query = em.createQuery("select p from PriceOverTime p where p.calendar = :time" , PriceOverTime.class);
        query.setParameter("time", calendar);
        System.out.println(query);
        System.out.println(query.getResultList());
        return query.getResultList();
    }
    public List<CryptoValutaDTO> getCryptoByName(String id){
        EntityManager em = getEntityManager();
        try {
            TypedQuery query = em.createQuery("Select c from CryptoValuta c where c.id= :id", CryptoValuta.class);
            query.setParameter("id", id);
            List<CryptoValuta> cv = query.getResultList();
            return CryptoValutaDTO.getCryptoValutaDTO(cv);
        } finally {
            em.close();
        }
    }
    public List<PoTDTO> changeCalculator(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)-1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        List<PriceOverTime> today = PoTList(calendar);

        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)-1);
        List<PriceOverTime>  yesterday = PoTList(calendar);
        List<PoTDTO> returnPots = new ArrayList<>();
        for(PriceOverTime potYesterday: yesterday){
            for(PriceOverTime potToday: today){
                if(potYesterday.getCoinName().equals(potToday.getCoinName())){
                    returnPots.add(new PoTDTO(potYesterday.getCoinName(), ((Float.parseFloat(potToday.getPrice())  / Float.parseFloat(potYesterday.getPrice()))-1)*100));
                }
            }
        }
        System.out.println(returnPots.get(2).getChange());
        return returnPots;
    }


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        emf = EMF_Creator.createEntityManagerFactory();
        CryptoFacade cf = getCryptoFacade(emf);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(HttpUtils.fetchcryptos(cf.getCryptoFromDB())));


    }

}
