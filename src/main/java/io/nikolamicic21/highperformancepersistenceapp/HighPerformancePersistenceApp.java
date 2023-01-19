package io.nikolamicic21.highperformancepersistenceapp;

import io.nikolamicic21.highperformancepersistenceapp.entity.elementcollection.ElemCollectionListPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.elementcollection.ElemCollectionSetPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.manytomany.BiManyToManyPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.manytomany.BiManyToManyTag;
import io.nikolamicic21.highperformancepersistenceapp.entity.manytomany.UniManyToManySetPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.manytomany.UniManyToManySetTag;
import io.nikolamicic21.highperformancepersistenceapp.entity.manytoone.unidirectionalmanytoone.UniManyToOnePost;
import io.nikolamicic21.highperformancepersistenceapp.entity.manytoone.unidirectionalmanytoone.UniManyToOnePostComment;
import io.nikolamicic21.highperformancepersistenceapp.entity.onetomany.bidirectionalonetomany.BiOneToManyPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.onetomany.bidirectionalonetomany.BiOneToManyPostComment;
import io.nikolamicic21.highperformancepersistenceapp.entity.onetomany.unidirectionalonetomany.UniOneToManyPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.onetomany.unidirectionalonetomany.UniOneToManyPostComment;
import io.nikolamicic21.highperformancepersistenceapp.entity.onetomany.unidirectionalonetomanyset.UniOneToManySetPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.onetomany.unidirectionalonetomanyset.UniOneToManySetPostComment;
import io.nikolamicic21.highperformancepersistenceapp.entity.onetoone.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class HighPerformancePersistenceApp {

    private static final Logger LOG = LoggerFactory.getLogger(HighPerformancePersistenceApp.class);

    public static void main(String[] args) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em = emFactory.createEntityManager();

//        executeUnidirectionalManyToOneRoutine(em);
//        executeBidirectionalOneToManyRoutine(em);
//        executeUnidirectionalOneToManyRoutine(em);
//        executeUnidirectionalOneToManySetRoutine(em);
//        executeElementCollectionListRoutine(em);
//        executeElementCollectionSetRoutine(em);
//        executeUnidirectionalOneToOneRoutine(em);
//        executeUnidirectionalOneToOneMapsIdRoutine(em);
//        executeBidirectionalOneToOneRoutine(em);
//        executeUnidirectionalManyToManySetRoutine(em);
//        executeBidirectionalManyToManyRoutine(em);

        em.close();
        emFactory.close();
    }

    private static void executeUnidirectionalManyToOneRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var post1 = new UniManyToOnePost();
            post1.setName("Post 1");
            it.persist(post1);

            final var post1Comment1 = new UniManyToOnePostComment();
            post1Comment1.setMessage("Post 1 Comment 1");
            post1Comment1.setPost(post1);

            final var post1Comment2 = new UniManyToOnePostComment();
            post1Comment2.setMessage("Post 1 Comment 2");
            post1Comment2.setPost(post1);

            it.persist(post1Comment1);
            it.persist(post1Comment2);
        });
    }

    private static void executeBidirectionalOneToManyRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var post1 = new BiOneToManyPost();
            post1.setName("Post 1");

            final var post1Comment1 = new BiOneToManyPostComment();
            post1Comment1.setMessage("Post 1 Comment 1");

            final var post1Comment2 = new BiOneToManyPostComment();
            post1Comment2.setMessage("Post 1 Comment 2");

            post1.addPostComment(post1Comment1);
            post1.addPostComment(post1Comment2);

            it.persist(post1);
        });
    }

    private static void executeUnidirectionalOneToManyRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var post1 = new UniOneToManyPost();
            post1.setName("Post 1");

            final var post1Comment1 = new UniOneToManyPostComment();
            post1Comment1.setMessage("Post 1 Comment 1");

            final var post1Comment2 = new UniOneToManyPostComment();
            post1Comment2.setMessage("Post 1 Comment 2");

            post1.addPostComment(post1Comment1);
            post1.addPostComment(post1Comment2);

            it.persist(post1);
        });
    }

    private static void executeUnidirectionalOneToManySetRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var post1 = new UniOneToManySetPost();
            post1.setName("Post 1");

            final var post1Comment1 = new UniOneToManySetPostComment("Post 1 Comment 1");

            final var post1Comment2 = new UniOneToManySetPostComment("Post 1 Comment 2");

            post1.getUniOneToManySetPostComments().add(post1Comment1);
            post1.getUniOneToManySetPostComments().add(post1Comment2);

            it.persist(post1);
        });
    }

    private static void executeElementCollectionListRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var post1 = new ElemCollectionListPost();
            post1.setName("Post 1");
            post1.getComments().add("Post 1 Comment 1");
            post1.getComments().add("Post 1 Comment 2");

            it.persist(post1);
        });
    }

    private static void executeElementCollectionSetRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var post1 = new ElemCollectionSetPost();
            post1.setName("Post 1");
            post1.getComments().add("Post 1 Comment 1");
            post1.getComments().add("Post 1 Comment 2");

            it.persist(post1);
        });
    }

    private static void executeUnidirectionalOneToOneRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var post1 = new UniOneToOnePost();
            post1.setName("Post 1");

            final var post1comment1 = new UniOneToOnePostComment();
            post1comment1.setMessage("Post 1 Comment 1");
            post1comment1.setPost(post1);

            it.persist(post1);
            it.persist(post1comment1);
        });
    }

    private static void executeUnidirectionalOneToOneMapsIdRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var post1 = new UniOneToOnePost();
            post1.setName("Post 1");

            final var post1comment1 = new UniOneToOneMapsIdPostComment();
            post1comment1.setMessage("Post 1 Comment 1");
            post1comment1.setPost(post1);

            it.persist(post1);
            it.persist(post1comment1);
        });
    }

    private static void executeBidirectionalOneToOneRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var post1comment1 = new BiOneToOnePostComment();
            post1comment1.setMessage("Post 1 Comment 1");

            final var post1 = new BiOneToOnePost();
            post1.setName("Post 1");
            post1.setComment(post1comment1);

            it.persist(post1);
        });
    }

    private static void executeUnidirectionalManyToManySetRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var post1 = new UniManyToManySetPost("Post 1");
            final var post2 = new UniManyToManySetPost("Post 2");

            final var tag1 = new UniManyToManySetTag("Tag 1");
            final var tag2 = new UniManyToManySetTag("Tag 2");

            post1.getTags().add(tag1);
            post1.getTags().add(tag2);
            post2.getTags().add(tag1);

            it.persist(post1);
            it.persist(post2);
        });
    }

    private static void executeBidirectionalManyToManyRoutine(EntityManager em) {
        doInTransaction(em, it -> {
            final var post1 = new BiManyToManyPost("Post 1");
            final var post2 = new BiManyToManyPost("Post 2");

            final var tag1 = new BiManyToManyTag("Tag 1");
            final var tag2 = new BiManyToManyTag("Tag 2");

            post1.addTag(tag1);
            post1.addTag(tag2);
            post2.addTag(tag1);

            it.persist(post1);
            it.persist(post2);
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
