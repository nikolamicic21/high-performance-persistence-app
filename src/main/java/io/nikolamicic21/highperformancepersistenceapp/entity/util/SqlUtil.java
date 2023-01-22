package io.nikolamicic21.highperformancepersistenceapp.entity.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class SqlUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SqlUtil.class);

    public static void doInTransaction(EntityManagerFactory emFactory, Consumer<EntityManager> action) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            action.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            LOG.error("Error happened", ex);
            em.getTransaction().rollback();
        } finally {
            // DO NOTHING
            em.close();
        }
    }
}
