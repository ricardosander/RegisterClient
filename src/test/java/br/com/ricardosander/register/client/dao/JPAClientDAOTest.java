package br.com.ricardosander.register.client.dao;

import br.com.ricardosander.register.client.model.Client;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class JPAClientDAOTest {

    private final static Integer ID = 1;
    private final static String CPF = "123.456.789.01";
    private final static String CPF_UPDATED = "123.456.789.00";
    private final static String NAME = "Ricardo Sander";

    private static EntityManagerFactory emf;

    @BeforeClass
    public static void setUp() {
        emf = Persistence.createEntityManagerFactory("client-test");
    }

    @AfterClass
    public static  void shutdown() {
        emf.close();
    }

    @Test
    public void insert() {

        EntityManager entityManager = emf.createEntityManager();

        ClientDAO clientDAO = new JPAClientDAO(entityManager);

        Client client = new Client();
        client.setCpf(CPF);
        client.setNome(NAME);

        boolean result = clientDAO.insert(client);

        entityManager.close();

        assertTrue(result);
        assertNotNull(client.getId());
        assertEquals(ID, client.getId());
    }

    @Test
    public void find() {

        EntityManager entityManager = emf.createEntityManager();

        ClientDAO clientDAO = new JPAClientDAO(entityManager);
        Client client = clientDAO.find(ID);

        entityManager.close();

        assertNotNull(client);
        assertEquals(ID, client.getId());
        assertEquals(NAME, client.getNome());
        assertEquals(CPF, client.getCpf());
    }

    @Test
    public void testManagedState() {

        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        ClientDAO clientDAO = new JPAClientDAO(entityManager);
        Client client = clientDAO.find(ID);
        client.setCpf(CPF_UPDATED);

        entityManager.getTransaction().commit();

        entityManager.close();

        entityManager = emf.createEntityManager();

        clientDAO = new JPAClientDAO(entityManager);
        client = clientDAO.find(ID);

        assertNotNull(client);
        assertEquals(ID, client.getId());
        assertEquals(NAME, client.getNome());
        assertEquals(CPF_UPDATED, client.getCpf());
    }

}