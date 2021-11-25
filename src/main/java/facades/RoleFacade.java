package facades;


import entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class RoleFacade {
    private static RoleFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private RoleFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static RoleFacade getRoleFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RoleFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Role getRolesByName(String name){
        EntityManager em = getEntityManager();
        try{

            TypedQuery query = em.createQuery("select r from Role r where r.roleName = 'user'", Role.class);

            System.out.println(query.getSingleResult());
            Role role = (Role) query.getSingleResult();
            return role;
        } catch (NoResultException nre){
            return null;
        } finally
        {
            em.close();
        }
    }
}
