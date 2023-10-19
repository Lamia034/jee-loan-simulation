package com.bank.Connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connection {
    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;

    private static Connection c = null;

    private Connection(){

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("default");
            entityManager = entityManagerFactory.createEntityManager();

            System.out.println("Connected to the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Connection getInstance(){
        if(c == null){
            c = new Connection();
            return  c;
        }
        return c;
    }

    public static EntityManager getManager(){

        return c.entityManager;
    }
}
