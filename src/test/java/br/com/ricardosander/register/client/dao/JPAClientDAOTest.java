package br.com.ricardosander.register.client.dao;

import br.com.ricardosander.register.client.model.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class JPAClientDAOTest {

    EntityManagerFactory emf;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("client-test");
    }

    @After
    public void shutdown() {
        emf.close();
    }

    @Test
    public void insert() {

        EntityManager entityManager = emf.createEntityManager();

        JPAClientDAO jpaClientDAO = new JPAClientDAO(entityManager);

        Client client = new Client();
        client.setCpf("123.456.79.00");
        client.setNome("Ricardo Sander");

        boolean result = jpaClientDAO.insert(client);

        entityManager.close();

        assertTrue(result);
        assertNotNull(client.getId());
    }
}