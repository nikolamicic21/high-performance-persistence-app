package io.nikolamicic21.highperformancepersistenceapp;

import io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetomany.bidirectionalonetomany.BiOneToManyPost;
import io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetomany.bidirectionalonetomany.BiOneToManyPostComment;
import io.nikolamicic21.highperformancepersistenceapp.entity.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Consumer;

public class HighPerformancePersistenceApp {

    private static final Logger LOG = LoggerFactory.getLogger(HighPerformancePersistenceApp.class);

    public static void main(String[] args) {
        final var emFactory = EntityManagerUtil.newEntityManagerFactory();
        EntityManager em = emFactory.createEntityManager();

//        executeBatchInsertRouting(em);
//        executeBatchUpdateRouting(em);
//        executeBatchDeleteRouting(em);

        em.close();
        emFactory.close();
    }

    private static void executeBatchInsertRouting(EntityManager em) {
        doInTransaction(em, it -> {
            final var post1 = new BiOneToManyPost();
            post1.setName("Post");

            final var postComment1 = new BiOneToManyPostComment();
            postComment1.setMessage("post1postComment1");
            post1.addPostComment(postComment1);

            final var post2 = new BiOneToManyPost();
            post2.setName("Post");

            final var postComment2 = new BiOneToManyPostComment();
            postComment2.setMessage("post2postComment2");
            post2.addPostComment(postComment2);

            final var post3 = new BiOneToManyPost();
            post3.setName("Post");

            final var postComment3 = new BiOneToManyPostComment();
            postComment3.setMessage("post2postComment2");
            post3.addPostComment(postComment3);

            it.persist(post1);
            it.persist(post2);
            it.persist(post3);
        });
    }

    private static void executeBatchUpdateRouting(EntityManager em) {
        doInTransaction(em, it -> {
            List<BiOneToManyPostComment> comments = it.createQuery(
                    "select comment from BiOneToManyPostComment comment join fetch comment.post",
                    BiOneToManyPostComment.class
            ).getResultList();

            comments.forEach(comment -> {
                comment.setMessage(comment.getMessage() + "!");
                final var post = comment.getPost();
                post.setName(post.getName() + "!");
            });
        });
    }

    private static void executeBatchDeleteRouting(EntityManager em) {
        doInTransaction(em, it -> {
            List<BiOneToManyPost> posts = it.createQuery(
                    "select post from BiOneToManyPost post join fetch post.postComments",
                    BiOneToManyPost.class
            ).getResultList();

            posts.forEach(post -> {
                for (final var commentIter = post.getPostComments().iterator(); commentIter.hasNext();) {
                    final var comment = commentIter.next();
                    comment.setPost(null);
                    commentIter.remove();
                    it.remove(comment);
                }
            });
            it.flush();
            posts.forEach(it::remove);
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
