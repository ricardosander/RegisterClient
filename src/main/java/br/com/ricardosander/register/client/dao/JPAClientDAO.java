package br.com.ricardosander.register.client.dao;

import br.com.ricardosander.register.client.model.Client;

import javax.persistence.EntityManager;

public class JPAClientDAO implements ClientDAO {

    private final EntityManager entityManager;

    public JPAClientDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(Client client) {

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();

        return true;
    }

    @Override
    public Client find(Integer id) {
        return entityManager.find(Client.class, id);
    }

}
