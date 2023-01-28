package io.nikolamicic21.highperformancepersistenceapp.entity.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class SqlUtil {

    private final static ExecutorService executorService = Executors.newSingleThreadExecutor(r -> {
        Thread bob = new Thread(r);
        bob.setName("Sync");
        return bob;
    });

    private static final Logger LOG = LoggerFactory.getLogger(SqlUtil.class);

    public static void close() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

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

    public static void executeSync(Runnable callable) {
        try {
            executorService.submit(callable).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
