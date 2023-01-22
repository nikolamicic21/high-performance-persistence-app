package io.nikolamicic21.highperformancepersistenceapp;

import io.nikolamicic21.highperformancepersistenceapp.entity.dto.PostCommentSummary;
import io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetomany.bidirectionalonetomany.BiOneToManyPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetomany.bidirectionalonetomany.BiOneToManyPostComment;
import io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetomany.bidirectionalonetomany.BiOneToManyPostComment_;
import io.nikolamicic21.highperformancepersistenceapp.entity.util.EntityManagerUtil;
import io.nikolamicic21.highperformancepersistenceapp.entity.util.SqlStatementCountValidator;
import io.nikolamicic21.highperformancepersistenceapp.entity.util.SqlUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HighPerformancePersistenceApp {

    private static final Logger LOG = LoggerFactory.getLogger(HighPerformancePersistenceApp.class);

    private static EntityManagerFactory emFactory;

    public static void main(String[] args) {
        emFactory = EntityManagerUtil.newEntityManagerFactory();
        emFactory.close();
    }

    private static void executeFetchDtoAndPaginationJpqlRoutine() {
        SqlUtil.doInTransaction(emFactory, it -> {
            final var postCommentSummaries = it.createQuery(
                            "select new io.nikolamicic21.highperformancepersistenceapp.entity.dto.PostCommentSummary(p.id, p.name, c.message) " +
                                    "from BiOneToManyPostComment c " +
                                    "join c.post p " +
                                    "order by p.id"
                    )
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
        });
    }

    private static void executeFetchDtoAndPaginationNamedQueryRoutine() {
        SqlUtil.doInTransaction(emFactory, it -> {
            final var postCommentSummaries = it.createNamedQuery("PostCommentSummary")
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
        });
    }

    private static void executeFetchDtoAndPaginationNativeSqlQueryRoutine() {
        SqlUtil.doInTransaction(emFactory, it -> {
            final var postCommentSummaries = it.unwrap(Session.class).createNativeQuery(
                            "select p.id as id, p.name as title, c.message as review " +
                                    "from bi_one_to_many_post_comment c " +
                                    "join bi_one_to_many_post p on c.post_id = p.id " +
                                    "order by p.id"
                    )
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .setResultListTransformer(new AliasToBeanResultTransformer(PostCommentSummary.class))
                    .list();
            System.out.println(postCommentSummaries.size());
        });
    }

    private static void executeFetchCustomEntityGraphRoutine() {
        SqlUtil.doInTransaction(emFactory, it -> {
            SqlStatementCountValidator.reset();
            final var postCommentGraph = it.createEntityGraph(
                    BiOneToManyPostComment.class
            );
            postCommentGraph.addAttributeNodes(BiOneToManyPostComment_.post);

            final var postComment = it.find(
                    BiOneToManyPostComment.class,
                    181L,
                    Map.of("javax.persistence.fetchgraph", postCommentGraph)
            );
            postComment.getPost().getName();
            SqlStatementCountValidator.assertSelectCount(1);
        });
    }

    private static void executeFetchLazyInitializationExceptionRoutine() {
        BiOneToManyPost post = null;
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            final var postComment = em.find(BiOneToManyPostComment.class, 181L);
            post = postComment.getPost();
            em.getTransaction().commit();
        } catch (Exception ex) {
            LOG.error("Error happened", ex);
            em.getTransaction().rollback();
        } finally {
            // DO NOTHING
            em.close();
        }
        post.getName();
    }
}
