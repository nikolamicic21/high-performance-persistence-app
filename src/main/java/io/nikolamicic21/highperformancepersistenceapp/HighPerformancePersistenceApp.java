package io.nikolamicic21.highperformancepersistenceapp;

import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.joined.JoinedAnnouncement;
import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.joined.JoinedBoard;
import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.joined.JoinedPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.joined.JoinedTopicStatistics;
import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.mappedsuperclass.*;
import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.singletable.SingleTableAnnouncement;
import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.singletable.SingleTableBoard;
import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.singletable.SingleTablePost;
import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.singletable.SingleTableTopicStatistics;
import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.tableperclass.TablePerClassAnnouncement;
import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.tableperclass.TablePerClassBoard;
import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.tableperclass.TablePerClassPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.inheritance.tableperclass.TablePerClassTopicStatistics;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.function.Consumer;

public class HighPerformancePersistenceApp {

    private static final Logger LOG = LoggerFactory.getLogger(HighPerformancePersistenceApp.class);

    public static void main(String[] args) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em = emFactory.createEntityManager();

//        executeSingleTableRoutine(em);
//        executeJoinedRoutine(em);
//        executeTablePerClassRoutine(em);
//        executeMapperSuperclassRoutine(em);

        em.close();
        emFactory.close();
    }

    private static void executeSingleTableRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var board = new SingleTableBoard(
                    "Board"
            );
            it.persist(board);

            final var post = new SingleTablePost(
                    "Inheritance",
                    "John Doe",
                    board,
                    "Best practices"
            );

            it.persist(post);

            final var announcement = new SingleTableAnnouncement(
                    "Release x.y.z",
                    "John Doe",
                    board,
                    Timestamp.valueOf(LocalDateTime.now().plusMonths(1L))
            );

            it.persist(announcement);

            final var postStatistics = new SingleTableTopicStatistics(post);
            postStatistics.incrementViews();
            it.persist(postStatistics);

            final var announcementStatistics = new SingleTableTopicStatistics(announcement);
            announcementStatistics.incrementViews();
            it.persist(announcementStatistics);
        });
    }

    private static void executeJoinedRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var board = new JoinedBoard(
                    "Board"
            );
            it.persist(board);

            final var post = new JoinedPost(
                    "Inheritance",
                    "John Doe",
                    board,
                    "Best practices"
            );

            it.persist(post);

            final var announcement = new JoinedAnnouncement(
                    "Release x.y.z",
                    "John Doe",
                    board,
                    Timestamp.valueOf(LocalDateTime.now().plusMonths(1L))
            );

            it.persist(announcement);

            final var postStatistics = new JoinedTopicStatistics(post);
            postStatistics.incrementViews();
            it.persist(postStatistics);

            final var announcementStatistics = new JoinedTopicStatistics(announcement);
            announcementStatistics.incrementViews();
            it.persist(announcementStatistics);
        });
    }

    private static void executeTablePerClassRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var board = new TablePerClassBoard(
                    "Board"
            );
            it.persist(board);

            final var post = new TablePerClassPost(
                    "Inheritance",
                    "John Doe",
                    board,
                    "Best practices"
            );

            it.persist(post);

            final var announcement = new TablePerClassAnnouncement(
                    "Release x.y.z",
                    "John Doe",
                    board,
                    Timestamp.valueOf(LocalDateTime.now().plusMonths(1L))
            );

            it.persist(announcement);

            final var postStatistics = new TablePerClassTopicStatistics(post);
            postStatistics.incrementViews();
            it.persist(postStatistics);

            final var announcementStatistics = new TablePerClassTopicStatistics(announcement);
            announcementStatistics.incrementViews();
            it.persist(announcementStatistics);
        });
    }

    private static void executeMapperSuperclassRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var board = new MappedSuperclassBoard(
                    "Board"
            );
            it.persist(board);

            final var post = new MappedSuperclassPost(
                    "Inheritance",
                    "John Doe",
                    board,
                    "Best practices"
            );

            it.persist(post);

            final var announcement = new MappedSuperclassAnnouncement(
                    "Release x.y.z",
                    "John Doe",
                    board,
                    Timestamp.valueOf(LocalDateTime.now().plusMonths(1L))
            );

            it.persist(announcement);

            final var postStatistics = new MappedSuperclassPostStatistics(post);
            postStatistics.incrementViews();
            it.persist(postStatistics);

            final var announcementStatistics = new MappedSuperclassAnnouncementStatistics(announcement);
            announcementStatistics.incrementViews();
            it.persist(announcementStatistics);
        });
    }

    private static void doInTransaction(EntityManager em, Consumer<EntityManager> action) {
        try {
            em.getTransaction().begin();
            action.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            LOG.error("Error happened", ex);
            em.getTransaction().rollback();
        } finally {
            // DO NOTHING
        }
    }
}
