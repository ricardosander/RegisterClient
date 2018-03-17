package br.com.ricardosander.register.client.dao;

import br.com.ricardosander.register.client.model.Client;

public interface ClientDAO {

    boolean insert(Client client);

    Client find(Integer id);
}
