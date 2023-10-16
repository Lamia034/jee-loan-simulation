package com.bank.DAO;

import com.bank.Entity.Client;
import com.bank.Exception.DeleteException;
import com.bank.Exception.InsertionException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClientDAOImpl implements ClientDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Client> create(Client client) {
        try {
            if (client == null)
                throw new Exception("***** Impossible d'ajouter un client vide *****");
            entityManager.persist(client);
            return Optional.of(client);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public int delete(String code) {
        try {
            if (code.isEmpty())
                throw new Exception("***** CODE CLIENT EST VIDE *****");
            Client client = entityManager.find(Client.class, code);
            if (client != null) {
                entityManager.remove(client);
                return 1;
            } else {
                throw new DeleteException("***** AUCUN CLIENT N'EST SUPPRIMÉ *****");
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return 0;
    }

    @Override
    @Transactional
    public Optional<Client> update(Client client) {
        try {
            if (client == null)
                throw new Exception("***** Impossible de modifier un client vide *****");
            entityManager.merge(client);
            return Optional.of(client);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> findByCode(String code) {
        try {
            Client client = entityManager.find(Client.class, code);
            return Optional.ofNullable(client);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Client>> findAll() {
        try {
            List<Client> clients = entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList();
            return Optional.ofNullable(clients);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Client>> find(Client client) {
        try {
            List<Client> clients = entityManager.createQuery(String.format("SELECT c FROM Client c WHERE c.firstName LIKE :firstName AND c.lastName LIKE :lastName AND c.phone LIKE :phone AND c.address LIKE :address AND c.birthDay = :birthDay"), Client.class)
                    .setParameter("firstName", "%" + client.getFirstName() + "%")
                    .setParameter("lastName", "%" + client.getLastName() + "%")
                    .setParameter("phone", "%" + client.getPhone() + "%")
                    .setParameter("address", "%" + client.getAddress() + "%")
                    .setParameter("birthDay", java.sql.Date.valueOf(client.getBirthDay()))
                    .getResultList();
            return Optional.ofNullable(clients);
        } catch (Exception e) {
            System.out.println(e.getClass() + "::" + e.getMessage());
        }
        return Optional.empty();
    }
}
