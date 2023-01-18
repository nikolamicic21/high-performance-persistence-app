package io.nikolamicic21.highperformancepersistenceapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HighPerformancePersistenceApp {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em = emFactory.createEntityManager();
        em.close();
        emFactory.close();
    }

}
