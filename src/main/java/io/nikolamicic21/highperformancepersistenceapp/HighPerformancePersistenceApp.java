package io.nikolamicic21.highperformancepersistenceapp;

import io.nikolamicic21.highperformancepersistenceapp.entity.concurrencycontrol.optimisticlocks.OptimisticLocksBiOneToManyPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.concurrencycontrol.perimissticlocks.PessimisticLocksBiOneToManyPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.util.EntityManagerUtil;
import io.nikolamicic21.highperformancepersistenceapp.entity.util.SqlUtil;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HighPerformancePersistenceApp {

    private static final Logger LOG = LoggerFactory.getLogger(HighPerformancePersistenceApp.class);

    private static EntityManagerFactory emFactory;

    public static void main(String[] args) {
        emFactory = EntityManagerUtil.newEntityManagerFactory();

        executeInheritedCascadeLockRoutine();

        emFactory.close();
        SqlUtil.close();
    }

    private static void executeInsertUpdateRoutine() {
        SqlUtil.doInTransaction(emFactory, it -> {
            final var post = new OptimisticLocksBiOneToManyPost();
            post.setName("Post");

            it.persist(post);

            it.flush();
            post.setName("Post!!!");
        });
    }

    private static void executeSyncUpdateRoutine() {
        SqlUtil.doInTransaction(emFactory, it -> {
            final var post = it.find(OptimisticLocksBiOneToManyPost.class, 208L);
            SqlUtil.executeSync(() ->
                    SqlUtil.doInTransaction(emFactory, _it -> {
                        final var post1 = _it.find(OptimisticLocksBiOneToManyPost.class, 208L);
                        post1.setName("Post121");
                    })
            );
            post.setName("Post212");
        });
    }

    private static void executePessimisticWriteLockRoutine() {
        final var post = new PessimisticLocksBiOneToManyPost();
        SqlUtil.doInTransaction(emFactory, it -> {
            post.setName("post");

            it.persist(post);
        });

        SqlUtil.doInTransaction(emFactory, it -> {
            it.find(PessimisticLocksBiOneToManyPost.class, post.getId(), LockModeType.PESSIMISTIC_WRITE);
        });
    }

    private static void executePessimisticReadLockRoutine() {
        final var post = new PessimisticLocksBiOneToManyPost();
        SqlUtil.doInTransaction(emFactory, it -> {
            post.setName("post");

            it.persist(post);
        });

        SqlUtil.doInTransaction(emFactory, it -> {
            it.find(PessimisticLocksBiOneToManyPost.class, post.getId(), LockModeType.PESSIMISTIC_READ);
        });
    }

    private static void executeInheritedCascadeLockRoutine() {
        SqlUtil.doInTransaction(emFactory, it -> {
            it.find(PessimisticLocksBiOneToManyPost.class, 209L);
        });
    }
}
