package facades;

import dtos.UserDTO;
import entities.Role;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class UserFacadeTest {

    private static EntityManagerFactory emf;
    private static UserFacade facade;

    public UserFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = UserFacade.getUserFacade(emf);

    }

    @AfterAll
    public static void tearDownClass() {
    //Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Role> role = new ArrayList<>();
            Role testRole = new Role("user");
            role.add(testRole);
            User testPerson = new User("TestPerson", "TestPassword", role);

            em.persist(testPerson);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {


    }

    // TODO: Delete or change this method

    @Test
    public void testCreateUser() throws Exception{
        List<Role> role = new ArrayList<>();
        Role userRole = new Role("user");
        role.add(userRole);
        User John = new User("John", "Hansen", role);
        UserDTO John1 = new UserDTO(John);

        assertEquals("John", facade.createUser(John1).getUserName());
    }


}
